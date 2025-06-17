package io.csh.utils.agent;

import io.csh.utils.banner.AppBanner;
import io.csh.utils.banner.BorderStyle;
import io.csh.utils.logging.LoggingConfig;
import io.csh.utils.logging.Logger;
import io.csh.utils.logging.LoggerFactory;
import io.csh.utils.logging.annotation.CshLog;
import io.csh.utils.agent.transformers.QueryExecutionTransformer;
import io.csh.utils.agent.transformers.SetParameterTransformer;

import java.lang.instrument.Instrumentation;

@CshLog
public class AgentEntry {
    private static final Logger log = LoggerFactory.getLogger(AgentEntry.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        try {
            System.out.println(ascciiArtLogo());

            AppBanner banner = new AppBanner.Builder()
                    .name("DADP")
                    .version("1.0.0")
                    .customMessage("DADP (Dynamic Agent-driven Data Protection) Java Agent")
                    .borderStyle(BorderStyle.BOLD)         // 이중선 테두리 사용
                    .build();

            banner.print();

            LoggingConfig config = LoggingConfig.getInstance();
            System.out.println(config.printLoggingStatus());

            // Transformer 등록
            inst.addTransformer(new QueryExecutionTransformer());
            inst.addTransformer(new SetParameterTransformer());
        } catch (Exception e) {
            log.error("Error initializing DADP Java Agent: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 