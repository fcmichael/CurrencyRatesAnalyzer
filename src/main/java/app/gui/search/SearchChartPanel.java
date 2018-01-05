package app.gui.search;

import app.i18n.MessagesReader;
import app.nbp.model.Rate;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

import java.text.SimpleDateFormat;
import java.util.List;

class SearchChartPanel {

	static JFreeChart createChart(List<Rate> rateList, String title) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				title,
				MessagesReader.getInstance().getMessage("SearchChartXLabel"),
				MessagesReader.getInstance().getMessage("SearchChartYLabel"),
				createDataset(rateList),
				false,
				false,
				false
		);

		DateAxis axisX = (DateAxis) chart.getXYPlot().getDomainAxis();

		int size = rateList.size();
		int dateBreak;
		if(size <= 8){
			dateBreak = 1;
		} else {
			dateBreak = (1+(size/90))*5;
		}

		axisX.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, dateBreak));
		axisX.setTickMarkPosition(DateTickMarkPosition.START);
		axisX.setDateFormatOverride(new SimpleDateFormat("dd-MM"));

		chart.setAntiAlias(true);
		chart.setTextAntiAlias(true);

		return chart;
	}

	private static TimeSeriesCollection createDataset(List<Rate> rateList) {
		TimeSeries timeSeries = new TimeSeries("");

		rateList.stream()
				.map(rate -> new TimeSeriesDataItem(new Day(rate.getEffectiveDate()), rate.getMid()))
				.forEach(timeSeries::add);

		return new TimeSeriesCollection(timeSeries);
	}
}
