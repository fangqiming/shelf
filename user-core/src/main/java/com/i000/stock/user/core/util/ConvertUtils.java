package com.i000.stock.user.core.util;

import com.alibaba.fastjson.util.TypeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class ConvertUtils {

    private static final String CALLBACK_ERROR_MESSAGE = "callback 不能为空!";
    private static final String BEAN_CONVERT_ERROR_MESSAGE = "类型转换异常!";

    private ConvertUtils() {
    }

    /**
     * bean 转换方法,将source中的属性复制至dest中
     *
     * @param source      源对象
     * @param destination 目标对象
     * @return 转换后的对象
     */
    public static <T, M> T beanConvert(M source, T destination) {
        return (T) beanConvert(source, destination, EmptyConvertCallBack.EMPTY_CONVERT_CALLBACK);
    }

    /**
     * bean 转换方法,将source中的属性复制至destination中
     *
     * @param source      源对象
     * @param destination 目标对象
     * @param callback    回调对象
     * @param <T>         泛型对象
     * @return 转换后的对象
     */
    public static <T, M> T beanConvert(M source, T destination, ConvertCallback<T, M> callback) {
        assertCallback(callback, CALLBACK_ERROR_MESSAGE);

        BeanUtils.copyProperties(source, destination);
        callback.each(destination, source);
        return destination;
    }

    /**
     * 集合数据转换,sources若为null或者size为0,则返回一个空的集合
     *
     * @param <T>                    泛型
     * @param sources                数据源
     * @param destinationElementType 目标类Class对象
     * @return 转换成功后的数据集, 如果sources为null或者为空集合, 则返回一个空的集合
     */
    public static <T, M> List<T> listConvert(List<M> sources, Class<T> destinationElementType) {
        return listConvert(sources, destinationElementType,
                EmptyConvertCallBack.EMPTY_CONVERT_CALLBACK);
    }

    /**
     * 集合数据转换,sources若为null或者size为0,则返回一个空的集合
     *
     * @param sources                数据源
     * @param destinationElementType 目标类Class对象
     * @param callback               每次遍历对象转换完成后都会调用一次此对象的唯一方法,此参数不可为空,若传空则抛出BeanConvertException异常
     * @param <T>                    泛型
     * @return 转换成功后的数据集, 如果sources为null或者为空集合, 则返回一个空的集合
     */
    public static <T, M> List<T> listConvert(List<M> sources,
                                             Class<T> destinationElementType,
                                             ConvertCallback<T, M> callback) {
        assertCallback(callback, CALLBACK_ERROR_MESSAGE);

        if (Objects.isNull(sources) || sources.isEmpty()) {
            return Lists.newArrayList();
        }

        List<T> destinations = Lists.newArrayListWithCapacity(sources.size());
        try {
            for (M obj : sources) {
                T entity = destinationElementType.newInstance();
                BeanUtils.copyProperties(obj, entity);
                callback.each(entity, obj);
                destinations.add(entity);
            }
        } catch (InstantiationException | IllegalAccessException | BeansException e) {
            throw new BeanConvertException(BEAN_CONVERT_ERROR_MESSAGE, e);
        }
        return destinations;
    }

    /**
     * map集合数据转换,sources若为null或者size为0,则返回一个空的集合
     *
     * @param sources                数据源
     * @param destinationElementType 目标类Class对象
     * @param callback               每次遍历对象转换完成后都会调用一次此对象的唯一方法,此参数不可为空,若传空则抛出BeanConvertException异常
     * @param <T>                    泛型
     * @return 转换成功后的数据集, 如果sources为null或者为空集合, 则返回一个空的集合
     */
    public static <T, M extends Map<String, Object>> List<T> mapsConvert(List<M> sources,
                                                                         Class<T> destinationElementType,
                                                                         ConvertCallback<T, M> callback) {
        assertCallback(callback, CALLBACK_ERROR_MESSAGE);

        if (Objects.isNull(sources) || sources.isEmpty()) {
            return Lists.newArrayList();
        }

        List<T> destinations = Lists.newArrayListWithCapacity(sources.size());
        try {
            for (M map : sources) {
                T entity = TypeUtils.castToJavaBean(map, destinationElementType, null);
                callback.each(entity, map);
                destinations.add(entity);
            }
        } catch (Exception e) {
            throw new BeanConvertException(BEAN_CONVERT_ERROR_MESSAGE, e);
        }

        return destinations;
    }

    /**
     * 将map中的值转为具体的bean
     *
     * @param source                 数据源
     * @param destinationElementType 转换后的类型
     * @param callback               callback
     * @param <T>                    泛型
     * @param <M>                    泛型
     * @return 转换后的bean
     */
    public static <T, M extends Map<String, Object>> T mapConvert(M source,
                                                                  Class<T> destinationElementType,
                                                                  ConvertCallback<T, M> callback) {
        assertCallback(callback, CALLBACK_ERROR_MESSAGE);

        T entity = TypeUtils.castToJavaBean(source, destinationElementType, null);
        callback.each(entity, source);
        return entity;
    }

    private static void assertCallback(ConvertCallback callback, String message) {
        if (Objects.isNull(callback)) {
            throw new NullPointerException(message);
        }
    }

    /**
     * bean转换Callback
     *
     * @param <T> 目标对象泛型
     * @param <M> 源对象泛型
     */
    @FunctionalInterface
    public interface ConvertCallback<T, M> {

        /**
         * 在bean转换成功后的额外操作
         *
         * @param destination 目标对象
         * @param source      源对象
         */
        void each(T destination, M source);
    }

    /**
     * 空实现
     */
    private static class EmptyConvertCallBack<T, M> implements ConvertCallback<T, M> {

        private static final ConvertCallback EMPTY_CONVERT_CALLBACK = new EmptyConvertCallBack();

        private EmptyConvertCallBack() {
        }

        /**
         * nothing to do
         *
         * @param destination 目标对象
         * @param source      源对象
         */
        @Override
        public void each(T destination, M source) {
        }
    }

    private static class BeanConvertException extends RuntimeException {

        private static final long serialVersionUID = 9212585769838124107L;

        private BeanConvertException() {
        }

        private BeanConvertException(String message) {
            super(message);
        }

        private BeanConvertException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }

}