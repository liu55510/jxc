package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.GoodsVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.CustomerReturnListGoodsService;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SaleListGoodsService saleListGoodsService;

    @Autowired
    private CustomerReturnListGoodsService customerReturnListGoodsService;

    @Override
    public ServiceVO getCode() {

        // 获取当前商品最大编码
        String code = goodsDao.getMaxCode();

        // 在现有编码上加1
        Integer intCode = Integer.parseInt(code) + 1;

        // 将编码重新格式化为4位数字符串形式
        String unitCode = intCode.toString();

        for (int i = 4; i > intCode.toString().length(); i--) {

            unitCode = "0" + unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }

    @Override
    public List<GoodsVo> getGoodsStockPage(Long page, Integer rows, String codeOrName, Integer goodsTypeId) {
        page = page == 0 ? 1 : page;
        Long offset = (page - 1) * rows;//每一页的第一列数据的商品编号
        List<GoodsVo> goodsPage = goodsDao.getGoodsStockPage(offset, rows, codeOrName, goodsTypeId);

        goodsPage.stream().
                parallel().
                forEach(item -> {
                    Integer saleCount = saleListGoodsService.getSaleTotalByGoodsId(item.getGoodsId());
                    Integer returnCount = customerReturnListGoodsService.getCustomerReturnTotalByGoodsId(item.getGoodsId());
                    Integer saleTotal = saleCount - returnCount;
                    item.setSaleTotal(saleTotal);
                });
        return goodsPage;
    }

    @Override
    public long getGoodsTotal() {
        return goodsDao.findGoodsCount();
    }

    @Override
    public List<GoodsVo> getGoodsPage(Long page, Integer rows, String goodsName, Integer goodsTypeId) {
        page = page == 0 ? 1 : page;
        Long offset = (page - 1) * rows;//每一页的第一列数据的商品编号
        List<GoodsVo> goodsPage = goodsDao.getGoodsPage(offset, rows, goodsName, goodsTypeId);
        return goodsPage;
    }

    @Override
    public Long getGoodsCountByGoodsTypeId(Integer goodsTypeId) {
        return goodsDao.getGoodsCountByGoodsTypeId(goodsTypeId);
    }

    @Override
    public void save(Goods goods) {
        int code = this.getCode().getCode();

        goods.setInventoryQuantity(100);
        goods.setState(0);
        goods.setGoodsCode(code + "");
        goodsDao.save(goods);
    }

    @Override
    public void update(Integer goodsId, Goods goods) {
        int code = this.getCode().getCode();
        goods.setInventoryQuantity(100);
        goods.setState(0);
        goods.setGoodsCode(code + "");
        goodsDao.update(goodsId, goods);
    }

    @Override
    public void deleteById(Integer goodsId) {
        goodsDao.deleteById(goodsId);
    }

    @Override
    public List<GoodsVo> getNoInventoryQuantity(Long page, Integer rows, String nameOrCode) {
        page = page == 0 ? 1 : page;
        Long offset = (page - 1) * rows;//每一页的第一列数据的商品编号
        return goodsDao.getNoInventoryQuantity(offset, rows, nameOrCode);
    }

    @Override
    public long getNoInventoryQuantityCount() {
        return goodsDao.getNoInventoryQuantityCount();
    }

    @Override
    public List<GoodsVo> getHasInventoryQuantity(Long page, Integer rows, String nameOrCode) {
        page = page == 0 ? 1 : page;
        Long offset = (page - 1) * rows;//每一页的第一列数据的商品编号
        return goodsDao.getHasInventoryQuantity(offset, rows, nameOrCode);
    }

    @Override
    public long getHasInventoryQuantityCount() {
        return goodsDao.getHasInventoryQuantityCount();
    }

    @Override
    public void saveStock(Integer goodsId, Integer inventoryQuantity, Double purchasingPrice) {
        goodsDao.saveStock(goodsId, inventoryQuantity, purchasingPrice);
    }

    @Override
    public ServiceVO deleteStock(Integer goodsId) {
        ServiceVO serviceVO = new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        Goods goods = goodsDao.getGoodsById(goodsId);
        Integer state = goods.getState();
        if (state == 1) {
            serviceVO.setCode(ErrorCode.STORED_ERROR_CODE);
            serviceVO.setMsg(ErrorCode.STORED_ERROR_MESS);
            return serviceVO;
        }
        if (state == 2) {
            serviceVO.setCode(ErrorCode.HAS_FORM_ERROR_CODE);
            serviceVO.setMsg(ErrorCode.HAS_FORM_ERROR_MESS);
            return serviceVO;
        }
        goodsDao.deleteById(goodsId);
        return serviceVO;
    }

    @Override
    public List<GoodsVo> getGoodsListAlarm() {
        return goodsDao.getGoodsListAlarm();
    }


}
