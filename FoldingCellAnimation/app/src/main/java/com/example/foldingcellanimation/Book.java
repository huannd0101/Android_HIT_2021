package com.example.foldingcellanimation;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private Integer id;
    private String imageLink;
    private String imagePublicId;
    private String title;
    private String author;
    private String publisher;
    private Integer releaseYear;
    private Integer numOfPage;
    private String description;
    private String categoty;
    private Integer rateStar;
    private Integer price;
    private Integer numOfReview;

    public Book() {
    }

    public Book(Integer id, String imageLink, String imagePublicId, String title, String author, String publisher, Integer releaseYear, Integer numOfPage, String description, String categoty, Integer rateStar, Integer price, Integer numOfReview) {
        this.id = id;
        this.imageLink = imageLink;
        this.imagePublicId = imagePublicId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
        this.numOfPage = numOfPage;
        this.description = description;
        this.categoty = categoty;
        this.rateStar = rateStar;
        this.price = price;
        this.numOfReview = numOfReview;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    protected Book(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        imageLink = in.readString();
        imagePublicId = in.readString();
        title = in.readString();
        author = in.readString();
        publisher = in.readString();
        if (in.readByte() == 0) {
            releaseYear = null;
        } else {
            releaseYear = in.readInt();
        }
        if (in.readByte() == 0) {
            numOfPage = null;
        } else {
            numOfPage = in.readInt();
        }
        description = in.readString();
        categoty = in.readString();
        if (in.readByte() == 0) {
            rateStar = null;
        } else {
            rateStar = in.readInt();
        }
        if (in.readByte() == 0) {
            numOfReview = null;
        } else {
            numOfReview = in.readInt();
        }
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getNumOfPage() {
        return numOfPage;
    }

    public void setNumOfPage(Integer numOfPage) {
        this.numOfPage = numOfPage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoty() {
        return categoty;
    }

    public void setCategoty(String categoty) {
        this.categoty = categoty;
    }

    public Integer getRateStar() {
        return rateStar;
    }

    public void setRateStar(Integer rateStar) {
        this.rateStar = rateStar;
    }

    public Integer getNumOfReview() {
        return numOfReview;
    }

    public void setNumOfReview(Integer numOfReview) {
        this.numOfReview = numOfReview;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", imageLink='" + imageLink + '\'' +
                ", imagePublicId='" + imagePublicId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", releaseYear=" + releaseYear +
                ", numOfPage=" + numOfPage +
                ", description='" + description + '\'' +
                ", category='" + categoty + '\'' +
                ", rateStar=" + rateStar +
                ", numOfReview=" + numOfReview +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(imageLink);
        dest.writeString(imagePublicId);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(publisher);
        if (releaseYear == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(releaseYear);
        }
        if (numOfPage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numOfPage);
        }
        dest.writeString(description);
        dest.writeString(categoty);
        if (rateStar == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rateStar);
        }
        if (numOfReview == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numOfReview);
        }
    }
}
