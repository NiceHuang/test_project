package cn.hnx.pattern.facade;

/**
 * Created by viruser on 2019/9/25.
 */
public class FacadeOrder {

    private ProductService productService;
    private PayService payService;

    public FacadeOrder() {
        this.productService = new ProductService();
        this.payService = new PayService();
    }

    public void buyPhone() {
        this.productService.pick();
        System.out.println("添加购物车");
        this.payService.pay();
    }
}
