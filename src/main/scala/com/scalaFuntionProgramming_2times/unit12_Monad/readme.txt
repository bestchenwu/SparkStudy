本章描述了函数泛化Functor的原则
即map(x)(a=>a)=x


monad指的是满足identity和associative法则的最小集
identity指的是compose(f,unit)==compse(unit,f)
associative指的是Monad[A],Monad[B]是可以结合成Monad[(A,B)],例如
 def product[A, B](ma: F[A], mb: F[B]): F[(A, B)] = map2(ma, mb)((_, _))