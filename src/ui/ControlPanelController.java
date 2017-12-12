package ui;

import utils.UtilityArray;
import utils.enums.SortMethod;
import utils.enums.ArrayType;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import utils.MergeQueue;
import utils.Sorter;

// <editor-fold desc="Helper classes." defaultstate="collapsed">
/* TODO: Stop using this to do C-style pass by referenceerence.
 * Before I forget, ¤ is a valid identifiers for class names.
 * https://www.reddit.com/r/rust/comments/5penft/parallelizing_enjarify_in_go_and_rust/dcsgk7n/
 */
class ¤Integer {

    private Integer reference;

    public ¤Integer(Integer α) {
        reference = α;
    }

    public Integer get() {
        return reference;
    }

    public Integer set(Integer α) {
        this.reference = α;
        return this.get();
    }

    @Override
    public String toString() {
        return reference.toString();
    }
}
// </editor-fold>

public class ControlPanelController implements Initializable {

// <editor-fold desc="Private members." defaultstate="collapsed">
    @FXML
    private Sorter<Integer> sorter;
    @FXML
    private SortMethod sortMethod;
    /**/
    private UtilityArray<Integer> ua;
    @FXML
    private ArrayType arrayType;
    @FXML
    private TextField inputSizeField;
    @FXML
    private TextField blockSizeField;
// </editor-fold>

// <editor-fold desc="Setters for radio buttons." defaultstate="collapsed">
    @FXML
    private void setBubbleSort() {
        sortMethod = SortMethod.BUBBLE;
    }

    @FXML
    private void setInsertionSort() {
        sortMethod = SortMethod.INSERTION;
    }

    @FXML
    private void setQuickSort() {
        sortMethod = SortMethod.QUICK;
    }

    @FXML
    private void setSelectionSort() {
        sortMethod = SortMethod.SELECTION;
    }

    @FXML
    private void setAlreadySorted() {
        arrayType = ArrayType.ALREADY_SORTED;
    }

    @FXML
    private void setReverseOrder() {
        arrayType = ArrayType.REVERSE_ORDER;
    }

    @FXML
    private void setRandom() {
        arrayType = ArrayType.RANDOM;
    }
// </editor-fold>

// <editor-fold desc="Helper functions (input)." defaultstate="collapsed">
    private Integer getInputSize() throws NumberFormatException {
        return Integer.parseInt(inputSizeField.getText());
    }

    private Integer getBlockSize() throws NumberFormatException {
        return Integer.parseInt(blockSizeField.getText());
    }

    private void getInput(int[] pureInputSize, int[] pureBlockSize)
            throws Exception {
        if (inputSizeField.getText().trim().isEmpty()
                || blockSizeField.getText().trim().isEmpty()) {
            throw new Exception("Input size is empty or block size is empty, "
                    + "please specify a integral value.");
        }
        try {
            if ((pureBlockSize[0] = getBlockSize()) > getInputSize()
                    || getBlockSize() < 1
                    || (pureInputSize[0] = getInputSize()) < 3) {
                throw new Exception("Invalid input, the block size is either "
                        + "greater than the input size or is less than 1 or "
                        + "the input size is less than 3.");
            }
        } catch (NumberFormatException ex) {
            throw new Exception(ex.getMessage() + "\nYou MUST provide an "
                    + "integral value.");
        }

    }
// </editor-fold>

    @FXML
    private void handleGoButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        int[] pureInputSize = {0}, pureBlockSize = {0};

        try {
            getInput(pureInputSize, pureBlockSize);
        } catch (Exception ex) {
            alert.setHeaderText("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            return;
            /* Do not let error propagate. */
        }
        long start = System.currentTimeMillis();
        ua = new UtilityArray<Integer>(pureInputSize[0], pureBlockSize[0], arrayType);
        sorter = new Sorter<Integer>(ua);
        sorter.setSortMethod(sortMethod);
        sorter.execute();
        MergeQueue<Integer> mq = new MergeQueue<>(sorter.getSortedArrays());
        mq.execute();
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(mq.getMergedArray()));
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Finished");
        alert.setContentText("Sort completed in " + (end - start) + " ms");
//        System.out.println("Sorted time:" + (end - start));
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arrayType = ArrayType.RANDOM;
        sortMethod = SortMethod.SELECTION;
    }

}
