package control;

import core.Core;

import javax.swing.table.TableModel;

/**
 * @author charlottexiao
 */
public class ControlThread implements Runnable {

    private final String ncmFilePath;
    private final String outFilePath;
    private final TableModel model;
    private final int rowIndex;

    /**
     * ControllerThread初始化
     *
     * @param ncmFilePath ncm文件路径
     * @param outFilePath 输出路径
     */
    public ControlThread(String ncmFilePath, String outFilePath, TableModel model, int rowIndex) {
        this.ncmFilePath = ncmFilePath;
        this.outFilePath = outFilePath;
        this.model = model;
        this.rowIndex = rowIndex;
        model.setValueAt("转换中..", rowIndex, 3);
    }

    /**
     * 线程执行方法:NCM文件转换,并修改器转换状态
     */
    public void run() {
        if (new Core().ncm2Mp3(ncmFilePath, outFilePath)) {
            model.setValueAt("转换完毕", rowIndex, 3);
        } else {
            model.setValueAt("转换失败", rowIndex, 3);
        }
    }
}
