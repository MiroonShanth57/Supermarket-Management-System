package tdm;

public class PieChart {
    private String itemCode;
    private int ttlQty;

    public PieChart(String itemCode, int ttlQty) {
        this.itemCode = itemCode;
        this.ttlQty = ttlQty;
    }

    public PieChart() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getTtlQty() {
        return ttlQty;
    }

    public void setTtlQty(int ttlQty) {
        this.ttlQty = ttlQty;
    }

    @Override
    public String toString() {
        return "PieChartData{" +
                "itemCode='" + itemCode + '\'' +
                ", ttlQty=" + ttlQty +
                '}';
    }

}
