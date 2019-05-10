package basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class t_PieChartTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		PieChart piechart = new PieChart();
		
		//차트에 표시할 데이터 구성하기
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
					new PieChart.Data("포도", 10000),
					new PieChart.Data("사과", 18000),
					new PieChart.Data("배", 25000),
					new PieChart.Data("복숭아", 15000),
					new PieChart.Data("바나나", 5000),
					new PieChart.Data("귤", 12000)
				);
		piechart.setTitle("과일가격");
		piechart.setLabelLineLength(50);
		piechart.setLegendSide(Side.RIGHT); //범례가 나타날 위치 지정
		piechart.setData(pieChartData);		//데이터세팅
		
		Scene scene = new Scene(piechart, 500, 500);
		primaryStage.setTitle("PieChart 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
