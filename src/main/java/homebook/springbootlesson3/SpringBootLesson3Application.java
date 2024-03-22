package homebook.springbootlesson3;

import homebook.springbootlesson3.demo.SecondBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootLesson3Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootLesson3Application.class, args);

		context.getBean(SecondBean.class).postConstruct();


		/*
		1. контроллеры (controllers, api)
		2. Сервисный слой (services)
		3. Репозиторий (repository, dao)
		4. Сущности (entity, model)

		/book/**
		/reader/**
		/issue/**
		 */
	}

}
