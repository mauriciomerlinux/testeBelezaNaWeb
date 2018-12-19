package test.belezanaweb.com.br.testebelezanaweb.model;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    private Long sku;
    private String name;
    private Detail details;
    private Presentation presentation;
    private PriceSpecification priceSpecification;
    private List<ImageObject> imageObjects;
    private Brand brand;
    private Inventory inventory;

    public class Detail implements Serializable {
        private String shortDescription;

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }
    }

    public class Presentation implements Serializable {
        private Integer value;
        private String unit;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public class PriceSpecification implements Serializable {
        private Double price;
        private Double originalPrice;
        private Double maxPrice;
        private Double percent;
        private Double discount;
        private Installments installments;

        public class Installments implements Serializable {
            private Integer numberOfPayments;
            private Double monthlyPayment;

            public Integer getNumberOfPayments() {
                return numberOfPayments;
            }

            public void setNumberOfPayments(Integer numberOfPayments) {
                this.numberOfPayments = numberOfPayments;
            }

            public Double getMonthlyPayment() {
                return monthlyPayment;
            }

            public void setMonthlyPayment(Double monthlyPayment) {
                this.monthlyPayment = monthlyPayment;
            }
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(Double originalPrice) {
            this.originalPrice = originalPrice;
        }

        public Double getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(Double maxPrice) {
            this.maxPrice = maxPrice;
        }

        public Double getPercent() {
            return percent;
        }

        public void setPercent(Double percent) {
            this.percent = percent;
        }

        public Double getDiscount() {
            return discount;
        }

        public void setDiscount(Double discount) {
            this.discount = discount;
        }

        public Installments getInstallments() {
            return installments;
        }

        public void setInstallments(Installments installments) {
            this.installments = installments;
        }
    }

    public class ImageObject implements Serializable {
        private Boolean featured;
        private String thumbnail;
        private String small;
        private String medium;
        private String large;
        private String extraLarge;

        public Boolean getFeatured() {
            return featured;
        }

        public void setFeatured(Boolean featured) {
            this.featured = featured;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getExtraLarge() {
            return extraLarge;
        }

        public void setExtraLarge(String extraLarge) {
            this.extraLarge = extraLarge;
        }

    }

    public class Brand implements Serializable {
        private Line line;

        public Line getLine() {
            return line;
        }

        public void setLine(Line line) {
            this.line = line;
        }

        public class Line implements Serializable {
            private String description;
            private String name;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public class Inventory implements Serializable{
        private Integer quantity;

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Detail getDetails() {
        return details;
    }

    public void setDetails(Detail details) {
        this.details = details;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public PriceSpecification getPriceSpecification() {
        return priceSpecification;
    }

    public void setPriceSpecification(PriceSpecification priceSpecification) {
        this.priceSpecification = priceSpecification;
    }

    public List<ImageObject> getImageObjects() {
        return imageObjects;
    }

    public void setImageObjects(List<ImageObject> imageObjects) {
        this.imageObjects = imageObjects;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public ImageObject getImageObject() {
        for (ImageObject imageObject : imageObjects) {
            if (imageObject.featured) {
                return imageObject;
            }
        }

        return null;
    }

    /*
    "sku": "54417",
            "name": "Good Girl Velvet Fatale Carolina Herrera Eau de Parfum - Perfume Feminino 80ml"
            */


    /*
    "imageObjects": [
      {
        "featured": true,
        "thumbnail": "https://res.cloudinary.com/beleza-na-web/image/upload/w_50,f_auto,fl_progressive,q_auto:best/v1/imagens/5/good-girl-velvet-fatale-carolina-herrera-eau-de-parfum-perfume-feminino-80ml-54417-2883792442665122127.jpg",
        "small": "https://res.cloudinary.com/beleza-na-web/image/upload/w_100,f_auto,fl_progressive,q_auto:best/v1/imagens/5/good-girl-velvet-fatale-carolina-herrera-eau-de-parfum-perfume-feminino-80ml-54417-2883792442665122127.jpg",
        "medium": "https://res.cloudinary.com/beleza-na-web/image/upload/w_130,f_auto,fl_progressive,q_auto:best/v1/imagens/5/good-girl-velvet-fatale-carolina-herrera-eau-de-parfum-perfume-feminino-80ml-54417-2883792442665122127.jpg",
        "large": "https://res.cloudinary.com/beleza-na-web/image/upload/w_297,f_auto,fl_progressive,q_auto:best/v1/imagens/5/good-girl-velvet-fatale-carolina-herrera-eau-de-parfum-perfume-feminino-80ml-54417-2883792442665122127.jpg",
        "extraLarge": "https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:best/v1/imagens/5/good-girl-velvet-fatale-carolina-herrera-eau-de-parfum-perfume-feminino-80ml-54417-2883792442665122127.jpg"
      },
     */
}
