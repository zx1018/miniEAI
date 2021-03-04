package com.eai.FileTransfer.configuration;

import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/*
 * @Configuration : bean ��ü ���
 * @EnableAsync : �񵿱� ���μ��� ��뼱
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{
	
	//�⺻ Thread ��
	private static int TASK_CORE_POOL_SIZE = 1;
	//�ִ� Thread ��
	private static int TASK_MAX_POOL_SIZE = 2;
	//QUEUE ��
	private static int TASK_QUEUE_CAPACITY = 0;
	//Thread Bean Name
	private final String EXECUTOR_BEAN_NAME = "executor1";
	
	@Resource(name="executor1")
	private ThreadPoolTaskExecutor executor1;
	
	@Bean(name="executor1")
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(TASK_CORE_POOL_SIZE);
		executor.setMaxPoolSize(TASK_MAX_POOL_SIZE);
		executor.setQueueCapacity(TASK_QUEUE_CAPACITY);
		executor.setBeanName(EXECUTOR_BEAN_NAME);
		executor.setWaitForTasksToCompleteOnShutdown(false);
		executor.initialize();
		return executor;
	}
	
	/*
	 * Thread Process���� ���� �߻���
	 */
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncExceptionHandler();
	}
	
	/*
	 * task �������� pool�� á������ üũ
	 */
	public boolean checkSampleTaskExecute() {
		boolean result = true;
		
		System.out.println("Ȱ�� Task �� :::: " + executor1.getActiveCount());
		
		if(executor1.getActiveCount() >= (TASK_MAX_POOL_SIZE + TASK_QUEUE_CAPACITY)) {
			result = false;
		}
		return result;
	}
}