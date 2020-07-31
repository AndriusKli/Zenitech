package com.example.andriuskli;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.enums.PropertyType;
import com.example.andriuskli.service.BuildingService;
import com.example.andriuskli.service.OwnerService;
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
}
//    @Bean
//	public CommandLineRunner sampleData(OwnerService ownerService, BuildingService buildingService) {
//		return (args) -> {
//			ownerService.createOwner(new Owner(1L, "John", "Smith", new ArrayList<>()));
//            ownerService.createOwner(new Owner(2L, "Petras", "Petraitis", new ArrayList<>()));
//			buildingService.createBuilding(new Building(5L, "Vilnius", "Ateties", "5", new Owner(), 250.25, 55000.50, PropertyType.APARTMENT), 1L);
//            buildingService.createBuilding(new Building(3L, "Vilnius", "Ateties", "7", new Owner(), 200.00, 45000.00, PropertyType.INDUSTRIAL), 1L);
//            buildingService.createBuilding(new Building(6L, "Kaunas", "Rugiu", "3", new Owner(), 250.25, 55000.50, PropertyType.APARTMENT), 2L);
//            buildingService.createBuilding(new Building(7L, "Vilnius", "Kazlausko", "74", new Owner(), 200.00, 45000.00, PropertyType.INDUSTRIAL), 2L);
//            buildingService.createBuilding(new Building(8L, "Vilnius", "Ateties", "5-12", new Owner(), 250.25, 75500.00, PropertyType.APARTMENT), 2L);
//            buildingService.createBuilding(new Building(9L, "Vilnius", "Noragiskiu", "7", new Owner(), 200.00, 100000.00, PropertyType.HOUSE), 2L);
//        };
//	}
