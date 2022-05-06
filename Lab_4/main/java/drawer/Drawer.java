package drawer;

import data.InputFunction;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.lines.SeriesLines;

import java.awt.*;

public class Drawer {

    public static void drawResult(InputFunction oldFunc, InputFunction newFunc){
        double[] xOldFunc = oldFunc.getArrayX();
        double[] yOldFunc = oldFunc.getArrayY();

        double[] xNewFunc = newFunc.getArrayX();
        double[] yNewFunc = newFunc.getArrayY();


        XYChart chart = new XYChartBuilder()
                .width(1024)
                .height(768)
                .theme(Styler.ChartTheme.Matlab)
                .title("Lagrange Interpolation")
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();

        XYSeries oldFuncDraw = chart.addSeries("Исходная функция", xOldFunc, yOldFunc);
        oldFuncDraw.setLineStyle(SeriesLines.DASH_DASH);
        oldFuncDraw.setLineColor(Color.BLUE);

        XYSeries newFuncDraw = chart.addSeries("Новая фунцкия", xNewFunc, yNewFunc);
        newFuncDraw.setLineStyle(SeriesLines.SOLID);
        newFuncDraw.setLineColor(Color.RED);

        new SwingWrapper(chart).displayChart();
    }
}
