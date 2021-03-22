import com.jizhu.entity.po.ManageUserPO;
import com.jizhu.entity.vo.kvVO;
import com.jizhu.service.ManageUserService;
import com.jizhu.service.WarehouseIODetailService;
import com.jizhu.service.WarehouseOrderService;
import com.jizhu.service.WarehouseStockService;
import framework.ServiceTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ServiceTest extends ServiceTestBase {

    @Autowired
    private ManageUserService manageUserService;
    @Autowired
    private WarehouseOrderService warehouseOrderService;
    @Autowired
    private WarehouseStockService warehouseStockService;
    @Autowired
    private WarehouseIODetailService warehouseIODetailService;



    @Test
    public void getCustList(){

        List<kvVO> kvVOS = warehouseIODetailService.getCustList();

        for (kvVO kvVO : kvVOS){
            System.out.println(kvVO);
        }




    }

    @Test
    public void manageUserService(){
        ManageUserPO manageUserPO = manageUserService.getByUserName("511");
        System.out.println(manageUserPO);
    }

    @Test
    public void warehouseOrderService(){
        String sn = warehouseOrderService.getSn(new Date(), 1);
        System.out.println(sn);
    }

    @Test
    public void warehouseStockService(){
        int listCount = warehouseStockService.getListCount(1);

    }


}
