package developer.exam.live.vi.utils;

public class TestDataItem {

    private String test_question;
    private String test_option1;
    private String test_option2;
    private String test_option3;
    private float test_value1, test_value2, test_value3;
    private boolean isOptionCountTwo;

    public TestDataItem(String test_question, String test_option1, String test_option2, float test_value1, float test_value2, boolean isOptionCountTwo) {
        this.test_question = test_question;
        this.test_option1 = test_option1;
        this.test_option2 = test_option2;
        this.test_value1 = test_value1;
        this.test_value2 = test_value2;
        this.isOptionCountTwo = isOptionCountTwo;
    }

    public TestDataItem(String test_question, String test_option1, String test_option2, String test_option3, float test_value1, float test_value2, float test_value3, boolean isOptionCountTwo) {
        this.test_question = test_question;
        this.test_option1 = test_option1;
        this.test_option2 = test_option2;
        this.test_option3 = test_option3;
        this.test_value1 = test_value1;
        this.test_value2 = test_value2;
        this.test_value3 = test_value3;
        this.isOptionCountTwo = isOptionCountTwo;
    }

    public String getTest_question() {
        return test_question;
    }

    public String getTest_option1() {
        return test_option1;
    }

    public String getTest_option2() {
        return test_option2;
    }

    public String getTest_option3() {
        return test_option3;
    }

    public float getTest_value1() {
        return test_value1;
    }

    public float getTest_value2() {
        return test_value2;
    }

    public float getTest_value3() {
        return test_value3;
    }

    public boolean isOptionCountTwo() {
        return isOptionCountTwo;
    }
}
