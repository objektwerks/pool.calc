package pool

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

object Serializer:
  given JsonValueCodec[Entity] = JsonCodecMaker.make[Entity]( CodecMakerConfig.withDiscriminatorFieldName(None) )
  given JsonValueCodec[Account] = JsonCodecMaker.make[Account]( CodecMakerConfig.withDiscriminatorFieldName(None) )
  given JsonValueCodec[Pool] = JsonCodecMaker.make[Pool]( CodecMakerConfig.withDiscriminatorFieldName(None) )
  given JsonValueCodec[Cleaning] = JsonCodecMaker.make[Cleaning]( CodecMakerConfig.withDiscriminatorFieldName(None) )
  given JsonValueCodec[Measurement] = JsonCodecMaker.make[Measurement]( CodecMakerConfig.withDiscriminatorFieldName(None) )
  given JsonValueCodec[Chemical] = JsonCodecMaker.make[Chemical]( CodecMakerConfig.withDiscriminatorFieldName(None) )
