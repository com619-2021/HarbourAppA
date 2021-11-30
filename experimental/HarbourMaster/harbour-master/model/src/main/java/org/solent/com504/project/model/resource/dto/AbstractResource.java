package org.solent.com504.project.model.resource.dto;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.utilities.CharacteristicListToJsonConverter;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

// abstract class maps to two tables
// see https://stackoverflow.com/questions/997203/jpa-how-to-use-the-same-class-entity-to-map-different-tables
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class  AbstractResource {
    
    private Long id;

    private String href;

    private String uuid;

    private String name;

    private List<Characteristic> characteristics;

    private Party resourceOwner;

    private ResourceAccess resourceController;

    private String resourceTypeName;

    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // this avoids having to create a table for individual characteristis
    // but at the expense of not being able to search on characterists 
    @Column(name = "characteristics", length = 1000)
    @Convert(converter = CharacteristicListToJsonConverter.class)
    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    @OneToOne
    public Party getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(Party resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    public ResourceAccess getResourceController() {
        return resourceController;
    }

    public void setResourceController(ResourceAccess resourceController) {
        this.resourceController = resourceController;
    }

    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Resource{" + "id=" + id + ", href=" + href + ", uuid=" + uuid + ", name=" + name + ", characteristics=" + characteristics + ", resourceOwner=" + resourceOwner + ", resourceController=" + resourceController + ", resourceTypeName=" + resourceTypeName + ", description=" + description + '}';
    }
    
    
    
}
