//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.10 at 07:27:41 PM MEZ 
//


package de.tudresden.inf.rn.mobilis.gwtemulationserver.server.script;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for instanceGroupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="instanceGroupType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mobilis.inf.tu-dresden.de/XMLEmulationScript}abstractInstanceType">
 *       &lt;attribute name="instanceCount" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="firstInstanceId" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instanceGroupType")
public class InstanceGroupType
    extends AbstractInstanceType
{

    @XmlAttribute
    protected Integer instanceCount;
    @XmlAttribute
    protected Integer firstInstanceId;

    /**
     * Gets the value of the instanceCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInstanceCount() {
        return instanceCount;
    }

    /**
     * Sets the value of the instanceCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInstanceCount(Integer value) {
        this.instanceCount = value;
    }

    /**
     * Gets the value of the firstInstanceId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFirstInstanceId() {
        return firstInstanceId;
    }

    /**
     * Sets the value of the firstInstanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFirstInstanceId(Integer value) {
        this.firstInstanceId = value;
    }

}