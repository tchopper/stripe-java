package com.stripe.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.stripe.BaseStripeTest;
import com.stripe.net.APIResource;

import org.junit.Test;

public class SKUTest extends BaseStripeTest {
  @Test
  public void testDeserialize() throws Exception {
    final String data = getFixture("/v1/skus/sku_123");
    final SKU sku = APIResource.GSON.fromJson(data, SKU.class);
    assertNotNull(sku);
    assertNotNull(sku.getId());
    assertEquals("sku", sku.getObject());
    assertNull(sku.getProductObject());
  }

  @Test
  public void testDeserializeWithExpansions() throws Exception {
    final String[] expansions = { "product" };
    final String data = getFixture("/v1/skus/sku_123", expansions);
    final SKU sku = APIResource.GSON.fromJson(data, SKU.class);
    assertNotNull(sku);
    final Product product = sku.getProductObject();
    assertNotNull(product);
    assertNotNull(product.getId());
    assertEquals(sku.getProduct(), product.getId());
  }
}
