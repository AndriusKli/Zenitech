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
//            ownerService.createOwner(new Owner(1L, "John", "Smith", new ArrayList<>()));
//            ownerService.createOwner(new Owner(2L, "Petras", "Petraitis", new ArrayList<>()));
            buildingService.createBuilding(new Building(1L, "Vilnius", "Ateties", 250.25, 55000.50, PropertyType.APARTMENT));
            ownerService.createOwner(new Owner(2L, "Petras", "Petraitis", new ArrayList<>()));
            ownershipService.createOwnership(1L, new HashMap<String, Double>(Map.of("2", 1.0)));
//            buildingService.createBuilding(new Building(5L, "Vilnius", "Ateties", "7", 200.00, 45000.00, PropertyType.INDUSTRIAL));
//            buildingService.createBuilding(new Building(6L, "Kaunas", "Rugiu", "3", 250.25, 55000.50, PropertyType.APARTMENT));
//            buildingService.createBuilding(new Building(7L, "Vilnius", "Kazlausko", "74", 200.00, 45000.00, PropertyType.INDUSTRIAL));
//            buildingService.createBuilding(new Building(8L, "Vilnius", "Ateties", "5-12", 250.25, 75500.00, PropertyType.APARTMENT));
//            buildingService.createBuilding(new Building(9L, "Vilnius", "Noragiskiu", "7", 200.00, 100000.00, PropertyType.HOUSE));
//            ownershipRepository.save(new Ownership(1L, 4L, ownerService.getOwner(7L), 1.0));
//            ownershipRepository.save(new Ownership(2L, 5L, ownerService.getOwner(7L), 0.5));
//            ownershipRepository.save(new Ownership(2L, 5L, ownerService.getOwner(8L), 0.5));
        };
	}
}
