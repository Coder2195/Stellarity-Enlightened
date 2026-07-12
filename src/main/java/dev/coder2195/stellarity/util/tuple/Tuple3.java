package dev.coder2195.stellarity.util.tuple;

public record Tuple3<A, B, C>(A _1, B _2, C _3) {
	public static <A, B, C> Tuple3<A, B, C> from(Tuple2<A, B> tuple2, C _3) {
		return new Tuple3<>(tuple2._1(), tuple2._2(), _3);
	}

}
