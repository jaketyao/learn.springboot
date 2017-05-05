package com.bqjr.rocketmq.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangjb
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerConfig {
    private String instanceName;
	private String tranInstanceName;
    private String topic;
}
