package se.sdaproject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SampleModelController {
    @RequestMapping("/")
    public String sampleMapping() {
        return "Hello Giang";
    }

    @RequestMapping("/some/path")
    public String sampleMappingToSomePath() {
        return "What's for dinner?";
    }

    @RequestMapping("/some/path/{value}")
    public String sampleMappingWithVariables(@PathVariable String value) {
        return "The value of the variable 'value' is: " + value;
    }

    @RequestMapping("/some/path/{value}/{value}")
    public String sampleMappingWithMultipleVariables(@PathVariable String value) {
        return "The value of the variable 'value' is: " + value + value;
    }

    @RequestMapping(value = "/requestParams", params = {"someParam"})
    public String sampleMappingWithRequestParams(@RequestParam String someParam) {
        return "The value of your parameter is " + someParam;
    }

}
