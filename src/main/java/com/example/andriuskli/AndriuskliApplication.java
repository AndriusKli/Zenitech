package com.example.andriuskli;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.entity.Ownership;
import com.example.andriuskli.enums.PropertyType;
import com.example.andriuskli.repository.OwnershipRepository;
import com.example.andriuskli.service.BuildingService;
import com.example.andriuskli.service.OwnerService;
import com.example.andriuskli.service.OwnershipService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@EnableSwagger2
@SpringBootApplication
@EnableJpaAuditing
public class AndriuskliApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndriuskliApplication.class, args);
    }

    @Bean
    public Docket swaggerDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Documentation")
                .version("0.0.1 SNAPSHOT")
                .build();
    }

    @Bean
	public CommandLineRunner sampleData(OwnerService ownerService, BuildingService buildingService, OwnershipService ownershipService) {
		return (args) -> {
            buildingService.createBuilding(new Building(1L, "Vilnius", "Ateties", 250.25, 55000.50, PropertyType.APARTMENT));
            ownerService.createOwner(new Owner(2L, "Petras", "Petraitis", new ArrayList<>()));
            ownershipService.createOwnership(1L, new HashMap<String, Double>(Map.of("2", 1.0)));
            ownerService.createOwner(new Owner(3L, "John", "Smith", new ArrayList<>()));
            ownershipService.updateOwnership(3L, new HashMap<>(Map.of("5", 0.5)));
            buildingService.createBuilding(new Building(8L, "Kaunas", "Rugiu", 250.25, 55000.50, PropertyType.APARTMENT));

        };
	}
}
