package Problem4;
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T x, U y, V z);
}
//public interface TriFunction<T> {
//    T apply(T x, T y, T z);
//}

