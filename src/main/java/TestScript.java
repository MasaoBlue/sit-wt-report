public class TestScript {
  private int stepCount;
  private String caseName;
  private String filePath;
  
  public TestScript(String caseName, int stepCount, String filePath) {
    this.caseName = caseName;
    this.stepCount = stepCount;
    this.filePath = filePath;
  }
  
  public String formattedScriptInfo() {
    return filePath + " " + caseName;
  }

  public String formattedStepCount() {
    return stepCount + ""; 
  }

}