//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.23 at 02:34:40 PM IDT 
//


package xml.jaxb.schema.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="target-step" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="target-data" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="source-step" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="source-data" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ST-CustomMapping")
public class STCustomMapping {

    @XmlAttribute(name = "target-step", required = true)
    protected String targetStep;
    @XmlAttribute(name = "target-data", required = true)
    protected String targetData;
    @XmlAttribute(name = "source-step", required = true)
    protected String sourceStep;
    @XmlAttribute(name = "source-data", required = true)
    protected String sourceData;

    /**
     * Gets the value of the targetStep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetStep() {
        return targetStep;
    }

    /**
     * Sets the value of the targetStep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetStep(String value) {
        this.targetStep = value;
    }

    /**
     * Gets the value of the targetData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetData() {
        return targetData;
    }

    /**
     * Sets the value of the targetData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetData(String value) {
        this.targetData = value;
    }

    /**
     * Gets the value of the sourceStep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceStep() {
        return sourceStep;
    }

    /**
     * Sets the value of the sourceStep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceStep(String value) {
        this.sourceStep = value;
    }

    /**
     * Gets the value of the sourceData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceData() {
        return sourceData;
    }

    /**
     * Sets the value of the sourceData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceData(String value) {
        this.sourceData = value;
    }

}
