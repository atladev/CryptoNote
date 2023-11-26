<!-- CryptoNote.fxml -->
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Button?>
<?import javafx.scene.control.PasswordField?>
<?import javafx.scene.control.TextArea?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.VBox?>

<VBox xmlns="http://javafx.com/javafx"
      xmlns:fx="http://javafx.com/fxml"
      fx:controller="crypto.note.CryptoNoteController">

    <TextField fx:id="passwordField" promptText="Enter Password"/>
    <Button text="Login" onAction="#loginButtonAction"/>

    <TextArea fx:id="notesTextArea" editable="false"/>

    <Button text="Create Note" onAction="#createNoteButtonAction"/>
    <Button text="View Notes" onAction="#viewNotesButtonAction"/>
</VBox>