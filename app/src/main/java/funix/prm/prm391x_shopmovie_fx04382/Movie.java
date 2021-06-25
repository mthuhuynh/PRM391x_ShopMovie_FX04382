package funix.prm.prm391x_shopmovie_fx04382;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ravi on 21/12/17.
 */

public class Movie {
    String title;
    String image;
    String price;

    // public constructor
    public Movie(String title, String image, String price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // Methods which makes our class parcelable
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        image = in.readString();
        title = in.readString();
        price = in.readString();
    }


    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(title);
        parcel.writeString(price);
    }
}
