package com.brs.bookrentalsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brs.bookrentalsystem.dto.Purchase;
import com.brs.bookrentalsystem.mapper.PurchaseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseMapper purchaseMapper;

    @Transactional(readOnly = true)
    public List<Purchase> getAllPurchases() {
        return purchaseMapper.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Purchase> getPurchaseById(long id) {
        return purchaseMapper.findById(id);
    }

    @Transactional
    public Purchase createPurchase(Purchase purchase) {
        purchaseMapper.insertPurchase(purchase);
        return purchase;
    }

    @Transactional
    public Optional<Purchase> updatePurchase(long id, Purchase purchase) {
        purchase.setPurchaseId(id);
        int updated = purchaseMapper.updatePurchase(purchase);
        if (updated > 0) {
            return purchaseMapper.findById(id);
        }
        return Optional.empty();
    }

    // @Transactional
    // public boolean deletePurchase(long id) {
    // return purchaseMapper.deletePurchase(id) > 0;
    // }

    @Transactional(readOnly = true)
    public List<Purchase> getPurchasesByIsbn(String isbn) {
        return purchaseMapper.findByIsbn(isbn);
    }

}
