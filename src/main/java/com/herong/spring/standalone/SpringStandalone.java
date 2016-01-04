package com.herong.spring.standalone;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hr
 *
 */
public class SpringStandalone {

	private static ApplicationContext context;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpringStandalone.class);

	private static final CommandLineParser parser = new DefaultParser();
	private static final String contextFile = "/conf/springContext.xml";

	@Autowired
	private TaskService taskService;

	public static void main(String[] args) {
		try {

			Options options = new Options();

			options.addOption("h", "help", false, "使用帮助");
			options.addOption("s", "startup", false, "启动服务");

			CommandLine cmdline = parser.parse(options, args);
			if (cmdline.hasOption('h') || cmdline.hasOption("help")) {
				usage(args, options);
			}

			if (cmdline.hasOption('s') || cmdline.hasOption("startup")) {
				startup(args, options);
			}

		} catch (ParseException e) {
			LOGGER.error("系统导常", e);
		}

	}

	public static void usage(String args[], Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(args[0], options);
		System.exit(0);
	}

	public static void startup(String args[], Options options) {
		context = new ClassPathXmlApplicationContext(contextFile);
		System.out.println("startup");
		new SpringStandalone();
	}

	private SpringStandalone() {
		context.getAutowireCapableBeanFactory().autowireBean(this);
		taskService.start();
	}

}
