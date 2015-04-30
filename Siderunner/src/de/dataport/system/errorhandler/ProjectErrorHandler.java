package de.dataport.system.errorhandler;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**ErrorHandler f�r Fehler beim Einlesen einer Sherd-Diagrammdatei.
 * 
 * Hier wird bei jedem Fehler eine Exception geworfen.
 * 
 * @author Wolfgang Knauf
 *
 */
public class ProjectErrorHandler implements ErrorHandler
{
  /**Validierungsfehler. Fehler wird direkt weitergeworfen
   * @arg0 aufgetretener Fehler, wird direkt weitergeworfen
   * @exception SAXException �bergebener und weitergeworfener Fehler
   */
  @Override
  public void error(SAXParseException arg0) throws SAXException
  {
    throw arg0;
  }

  /**Fataler Fehler (z.B ung�ltiges XML). Fehler wird direkt weitergeworfen
   * @arg0 aufgetretener Fehler, wird direkt weitergeworfen
   * @exception SAXException �bergebener und weitergeworfener Fehler
   */
  @Override
  public void fatalError(SAXParseException arg0) throws SAXException
  {
    throw arg0;
  }

  /**Warnung. Fehler wird auch hier direkt weitergeworfen, da kein Fall bekannt, wo
   * das auftritt (und OK geht).
   * 
   * @arg0 aufgetretener Fehler, wird direkt weitergeworfen
   * @exception SAXException �bergebener und weitergeworfener Fehler
   */
  @Override
  public void warning(SAXParseException arg0) throws SAXException
  {
    throw arg0;
  }

}
