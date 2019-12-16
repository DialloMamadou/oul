package notification;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * @author malik
 *
 * cette classe gere les notifications qui seront affiche
 */
public class Notification {


    /**
     *
     * @param titre le titre de la notification
     * @param text le texte de la notification
     *
     *             cette methode gere le cas de succes de l action
     */
    public static void affichageSucces(String titre,String text){
        Image image=new Image("img/mooo.png");
        Notifications notification=Notifications.create()
                .title(titre)
                .text(text)
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_LEFT)
                .graphic(new ImageView(image));
        notification.darkStyle();
        notification.show();
    }


    /**
     *
     * @param titre le titre de la notification
     * @param text le texte de la notification
     *
     *             cette methode gere le cas d echec de l action
     */
    public static void affichageEchec(String titre,String text){
        Image image=new Image("img/delete.png");
        Notifications notification=Notifications.create()
                .title(titre)
                .text(text)
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_LEFT)
                .graphic(new ImageView(image));
        notification.darkStyle();
        notification.show();
    }
}
