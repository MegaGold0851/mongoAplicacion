module com.megagold.mongoennube_vuno {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;


    opens com.megagold.mongoennube_vuno to javafx.fxml;
    exports com.megagold.mongoennube_vuno;

    exports com.megagold.mongoennube_vuno.Core to javafx.fxml;
    opens com.megagold.mongoennube_vuno.Core to javafx.fxml;

    exports com.megagold.mongoennube_vuno.Model to javafx.fxml;
    opens com.megagold.mongoennube_vuno.Model to javafx.fxml, javafx.base;

}