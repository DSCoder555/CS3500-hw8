package cs3500.threetrios.providercode.controller;

import cs3500.threetrios.providercode.view.VisualThreeTriosView;

/**
 * Representation of a controller that has access to features that affect the behavior of the visual
 * view/GUI.
 */
public interface ITTController extends ReadingThreeTriosController {

  /**
   * Sets up the view and commences creating a visual view.
   * @param view the view that is set up with all its respective components and behaviors
   */
  void setView(VisualThreeTriosView view);

}
