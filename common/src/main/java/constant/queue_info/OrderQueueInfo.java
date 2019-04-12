package constant.queue_info;

/**
 * @author chc
 * @create 2019-01-18 17:05
 **/
public interface OrderQueueInfo {
    String EXCHANGE_VALUE="order-1";
    String EXCHANGE_IGNOREDECLARATIONEXCEPTIONS="true";
    String EXCHANGE_DURABLE="true";
    String EXCHANGE_TYPE="topic";
    String QUEUE_VALUE="spring_order";
    String QUEUE_DURABLE="true";
    String KEY="spring_order.test";

    String PRODUCT_QUEUE_VALUE="spring_order.test";
}
