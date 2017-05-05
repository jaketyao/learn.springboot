package com.bqjr.rocketmq.config;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangjb
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerConfig {
	private String instanceName;
    private List<String> subscribe;
}
