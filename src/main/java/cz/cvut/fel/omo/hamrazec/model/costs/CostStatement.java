package cz.cvut.fel.omo.hamrazec.model.costs;

public class CostStatement {
    private double kwPerTack;
    private double kwPrice;
    private double oilPerTack;
    private double oilPrice;
    private double petrolPerTack;
    private double petrolPrice;
    private double materialPerProduct;
    private double materialPrice;
    private int productAmount;
    private int tact;

    public CostStatement(double kwPerTack, double oilPerTack, double petrolPerTack, double materialPerProduct, int productAmount, int tact) {
        this.kwPerTack = kwPerTack;
        this.oilPerTack = oilPerTack;
        this.petrolPerTack = petrolPerTack;
        this.materialPerProduct = materialPerProduct;
        this.productAmount = productAmount;
        this.tact = tact;
    }

    public CostStatement setKwPrice(double kwPrice) {
        this.kwPrice = kwPrice;
        return this;
    }

    public double getKwPerTack() {
        return kwPerTack;
    }

    public double getKwPrice() {
        return kwPrice;
    }

    public double getElectricityCost(){
        return kwPerTack * kwPrice;
    }

    public CostStatement setOilPrice(double oilPrice) {
        this.oilPrice = oilPrice;
        return this;
    }

    public double getOilPerTack() {
        return oilPerTack;
    }

    public double getOilPrice() {
        return oilPrice;
    }

    public double getOilCost(){
        return oilPerTack * oilPrice;
    }

    public CostStatement setPetrolPrice(double petrolPrice) {
        this.petrolPrice = petrolPrice;
        return this;
    }

    public double getPetrolPerTack() {
        return petrolPerTack;
    }

    public double getPetrolPrice() {
        return petrolPrice;
    }

    public double getPetrolCost(){
        return petrolPerTack * petrolPrice;
    }

    public CostStatement setMaterialPrice(double materialPrice) {
        this.materialPrice = materialPrice;
        return this;
    }

    public double getMaterialPerProduct() {
        return materialPerProduct;
    }

    public double getMaterialPrice() {
        return materialPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public double getMaterialCost(){
        return materialPerProduct * productAmount * materialPrice;
    }

    public int getTact() {
        return tact;
    }
}
