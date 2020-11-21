package com.hnu.shopping.service;

import com.hnu.shopping.bean.PmsSearchParam;
import com.hnu.shopping.bean.PmsSearchSkuInfo;

import java.util.List;

public interface SearchService {
    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam);
}
