/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 *    ConvolutionLayer.java
 *    Copyright (C) 2016 University of Waikato, Hamilton, New Zealand
 *
 */
package weka.dl4j.layers;

import java.io.Serializable;
import java.util.Enumeration;
import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.OptionMetadata;
import weka.dl4j.activations.Activation;
import weka.dl4j.activations.ActivationIdentity;
import weka.gui.ProgrammaticProperty;

/**
 * A version of DeepLearning4j's ActivationLayer layer that implements WEKA option handling.
 *
 * @author Christopher Beckham
 * @author Eibe Frank
 */
public class ActivationLayer extends Layer<org.deeplearning4j.nn.conf.layers.ActivationLayer>
    implements OptionHandler, Serializable {


  private static final long serialVersionUID = 5492158451092620414L;

  /** Constructor for setting some defaults. */
  public ActivationLayer() {
    super();
    setLayerName("Activation layer");
  }

  @OptionMetadata(
      displayName = "activation function",
      description = "The activation function to use (default = Identity).",
      commandLineParamName = "activation",
      commandLineParamSynopsis = "-activation <specification>",
      displayOrder = 1
  )
  public Activation getActivationFunction() {
    return Activation.create(backend.getActivationFn());
  }

  public void setActivationFunction(Activation activationFn) {
    backend.setActivationFn(activationFn.getBackend());
  }

  /**
   * Global info.
   *
   * @return string describing this class.
   */
  public String globalInfo() {
    return "An activation layer from DeepLearning4J.";
  }




  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration<Option> listOptions() {
    return Option.listOptionsForClassHierarchy(this.getClass(),super.getClass()).elements();
  }

  /**
   * Gets the current settings of the Classifier.
   *
   * @return an array of strings suitable for passing to setOptions
   */
  @Override
  public String[] getOptions() {
    return Option.getOptionsForHierarchy(this, super.getClass());
  }

  /**
   * Parses a given list of options.
   *
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  public void setOptions(String[] options) throws Exception {
    Option.setOptionsForHierarchy(options, this, super.getClass());
  }

  @Override
  public void initializeBackend() {
    this.backend = new org.deeplearning4j.nn.conf.layers.ActivationLayer();
  }
}