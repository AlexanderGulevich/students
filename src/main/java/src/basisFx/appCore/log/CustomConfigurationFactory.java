//package basisFx.appCore.log;
//
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.core.Filter;
//import org.apache.logging.log4j.core.LoggerContext;
//import org.apache.logging.log4j.core.appender.ConsoleAppender;
//import org.apache.logging.log4j.core.config.*;
//import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
//import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
//import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
//import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
//import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
//import org.apache.logging.log4j.core.config.plugins.Plugin;
//
//import java.net.URI;
//
//@Plugin(name = "CustomConfigurationFactory", category = ConfigurationFactory.CATEGORY)
//@Order(50)
//public class CustomConfigurationFactory extends ConfigurationFactory {
//
//    static Configuration createConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
//
//        builder.setConfigurationName("RollingBuilder");
////
////        builder.setStatusLevel(Level.ERROR);
////        builder.addButEvent(builder.newFilter(
////                "ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL).addAttribute("level", Level.DEBUG));
////
////        AppenderComponentBuilder consoleBuilder = builder.newAppender("Stdout", "CONSOLE")
////                .addAttribute("dynamicElements", ConsoleAppender.DynamicContentPanel.SYSTEM_ERR);
////        consoleBuilder.addButEvent(builder.newLayout("PatternLayout")
////                .addAttribute("pattern", "%ServicePanelRightControls [%t] %-5level: %msg%n%throwable"));
////        consoleBuilder.addButEvent(builder.newFilter("MarkerFilter", Filter.Result.DENY,Filter.Result.NEUTRAL)
////                .addAttribute("marker", "FLOW"));
////        builder.addButEvent(consoleBuilder);
////
////        builder.addButEvent(builder.newLogger("org.apache.logging.log4j", Level.DEBUG)
////                .addButEvent(builder.newAppenderRef("Stdout"))
////                .addAttribute("additivity", false));
////        builder.addButEvent(builder.newRootLogger(Level.ERROR).addButEvent(builder.newAppenderRef("Stdout")));
//
//
//// configure a rolling file appender
//        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
//                .addAttribute("pattern", "%ServicePanelRightControls [%t] %-5level: %msg%n");
//
//        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
//                .addComponent(builder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 0 * * ?"))
//                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "2KB"));
//
//        AppenderComponentBuilder appenderBuilder = builder.newAppender("rolling", "RollingFile")
//                .addAttribute("fileName", "log/log.log")
//                .addAttribute("filePattern", "log/log-%ServicePanelRightControls{MM-dd-yy}.log.gz")
//                .add(layoutBuilder)
//                .addComponent(triggeringPolicy);
//        builder.add(appenderBuilder);
//
//// configure the new logger
//        builder.add( builder.newLogger( "TestLogger", Level.DEBUG )
//                .add( builder.newAppenderRef( "rolling" ) )
//                .addAttribute( "additivity", false ) );
//
//        builder.add( builder.newRootLogger( Level.DEBUG )
//                .add( builder.newAppenderRef( "rolling" ) ) );
////        LoggerContext ctx = Configurator.initialize(builder.build());
//
//
//        return builder.build();
//
//    }
//
//    @Override
//    public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
//        return getConfiguration(loggerContext, source.toString(), null);
//    }
//
//    @Override
//    public Configuration getConfiguration(final LoggerContext loggerContext, final String name, final URI configLocation) {
//        ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
//        return createConfiguration(name, builder);
//    }
//
//    @Override
//    protected String[] getSupportedTypes() {
//        return new String[] {"*"};
//    }
//}
//
