/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.voxloud.kafka.message.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class OrderCanceled extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2804755814923312376L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderCanceled\",\"namespace\":\"com.voxloud.kafka.message.avro\",\"fields\":[{\"name\":\"order_id\",\"type\":\"long\",\"doc\":\"Id of the order filed\"},{\"name\":\"timestamp\",\"type\":\"long\",\"doc\":\"Date of the cancellation\",\"logicalType\":\"date\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<OrderCanceled> ENCODER =
      new BinaryMessageEncoder<OrderCanceled>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrderCanceled> DECODER =
      new BinaryMessageDecoder<OrderCanceled>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<OrderCanceled> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<OrderCanceled> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OrderCanceled>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this OrderCanceled to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a OrderCanceled from a ByteBuffer. */
  public static OrderCanceled fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Id of the order filed */
  @Deprecated public long order_id;
  /** Date of the cancellation */
  @Deprecated public long timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrderCanceled() {}

  /**
   * All-args constructor.
   * @param order_id Id of the order filed
   * @param timestamp Date of the cancellation
   */
  public OrderCanceled(java.lang.Long order_id, java.lang.Long timestamp) {
    this.order_id = order_id;
    this.timestamp = timestamp;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return order_id;
    case 1: return timestamp;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: order_id = (java.lang.Long)value$; break;
    case 1: timestamp = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'order_id' field.
   * @return Id of the order filed
   */
  public java.lang.Long getOrderId() {
    return order_id;
  }

  /**
   * Sets the value of the 'order_id' field.
   * Id of the order filed
   * @param value the value to set.
   */
  public void setOrderId(java.lang.Long value) {
    this.order_id = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return Date of the cancellation
   */
  public java.lang.Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * Date of the cancellation
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.Long value) {
    this.timestamp = value;
  }

  /**
   * Creates a new OrderCanceled RecordBuilder.
   * @return A new OrderCanceled RecordBuilder
   */
  public static com.voxloud.kafka.message.avro.OrderCanceled.Builder newBuilder() {
    return new com.voxloud.kafka.message.avro.OrderCanceled.Builder();
  }

  /**
   * Creates a new OrderCanceled RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrderCanceled RecordBuilder
   */
  public static com.voxloud.kafka.message.avro.OrderCanceled.Builder newBuilder(com.voxloud.kafka.message.avro.OrderCanceled.Builder other) {
    return new com.voxloud.kafka.message.avro.OrderCanceled.Builder(other);
  }

  /**
   * Creates a new OrderCanceled RecordBuilder by copying an existing OrderCanceled instance.
   * @param other The existing instance to copy.
   * @return A new OrderCanceled RecordBuilder
   */
  public static com.voxloud.kafka.message.avro.OrderCanceled.Builder newBuilder(com.voxloud.kafka.message.avro.OrderCanceled other) {
    return new com.voxloud.kafka.message.avro.OrderCanceled.Builder(other);
  }

  /**
   * RecordBuilder for OrderCanceled instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrderCanceled>
    implements org.apache.avro.data.RecordBuilder<OrderCanceled> {

    /** Id of the order filed */
    private long order_id;
    /** Date of the cancellation */
    private long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.voxloud.kafka.message.avro.OrderCanceled.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.order_id)) {
        this.order_id = data().deepCopy(fields()[0].schema(), other.order_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[1].schema(), other.timestamp);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing OrderCanceled instance
     * @param other The existing instance to copy.
     */
    private Builder(com.voxloud.kafka.message.avro.OrderCanceled other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.order_id)) {
        this.order_id = data().deepCopy(fields()[0].schema(), other.order_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[1].schema(), other.timestamp);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'order_id' field.
      * Id of the order filed
      * @return The value.
      */
    public java.lang.Long getOrderId() {
      return order_id;
    }

    /**
      * Sets the value of the 'order_id' field.
      * Id of the order filed
      * @param value The value of 'order_id'.
      * @return This builder.
      */
    public com.voxloud.kafka.message.avro.OrderCanceled.Builder setOrderId(long value) {
      validate(fields()[0], value);
      this.order_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'order_id' field has been set.
      * Id of the order filed
      * @return True if the 'order_id' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'order_id' field.
      * Id of the order filed
      * @return This builder.
      */
    public com.voxloud.kafka.message.avro.OrderCanceled.Builder clearOrderId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * Date of the cancellation
      * @return The value.
      */
    public java.lang.Long getTimestamp() {
      return timestamp;
    }

    /**
      * Sets the value of the 'timestamp' field.
      * Date of the cancellation
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public com.voxloud.kafka.message.avro.OrderCanceled.Builder setTimestamp(long value) {
      validate(fields()[1], value);
      this.timestamp = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * Date of the cancellation
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * Date of the cancellation
      * @return This builder.
      */
    public com.voxloud.kafka.message.avro.OrderCanceled.Builder clearTimestamp() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrderCanceled build() {
      try {
        OrderCanceled record = new OrderCanceled();
        record.order_id = fieldSetFlags()[0] ? this.order_id : (java.lang.Long) defaultValue(fields()[0]);
        record.timestamp = fieldSetFlags()[1] ? this.timestamp : (java.lang.Long) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrderCanceled>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrderCanceled>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrderCanceled>
    READER$ = (org.apache.avro.io.DatumReader<OrderCanceled>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
