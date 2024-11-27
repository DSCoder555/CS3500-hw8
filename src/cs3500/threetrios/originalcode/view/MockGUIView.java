package cs3500.threetrios.originalcode.view;

import java.io.IOException;

/**
 * Mock representation of a GUI View for testing the controller.
 */
public class MockGUIView implements ThreeTriosGUI {
  Appendable log;

  public MockGUIView(Appendable log) {
    this.log = log;
    writeMessage("You created a view");
  }

  @Override
  public void addFeatureListener(ViewFeatures features) {
    writeMessage("You added a feature listener this view");
  }

  @Override
  public void startRendering() {
    writeMessage("You started rendering this view");
  }

  @Override
  public void display(boolean show) {
    writeMessage("You displayed this view");
  }

  @Override
  public void advance() {
    writeMessage("You updated this view");
  }

  @Override
  public void error() {
    //Not being tested
  }

  @Override
  public void displayMessage(String message) {
    writeMessage("You displayed this message: " + message);
  }

  @Override
  public void errorMessage(String message) {
    // Not being tested
  }

  private void writeMessage(String message) {
    try {
      log.append(message + "\n");
    } catch (IOException ignored) {
    }
  }
}
