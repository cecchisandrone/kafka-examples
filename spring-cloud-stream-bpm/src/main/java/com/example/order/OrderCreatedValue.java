/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.example.order;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class OrderCreatedValue extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1813411673226442326L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderCreatedValue\",\"namespace\":\"com.example.order\",\"fields\":[{\"name\":\"request_id\",\"type\":\"string\"},{\"name\":\"order_id\",\"type\":\"long\"},{\"name\":\"amount\",\"type\":\"int\"},{\"name\":\"item_id\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<OrderCreatedValue> ENCODER =
      new BinaryMessageEncoder<OrderCreatedValue>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrderCreatedValue> DECODER =
      new BinaryMessageDecoder<OrderCreatedValue>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<OrderCreatedValue> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<OrderCreatedValue> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OrderCreatedValue>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this OrderCreatedValue to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a OrderCreatedValue from a ByteBuffer. */
  public static OrderCreatedValue fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence request_id;
  @Deprecated public long order_id;
  @Deprecated public int amount;
  @Deprecated public java.lang.CharSequence item_id;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrderCreatedValue() {}

  /**
   * All-args constructor.
   * @param request_id The new value for request_id
   * @param order_id The new value for order_id
   * @param amount The new value for amount
   * @param item_id The new value for item_id
   */
  public OrderCreatedValue(java.lang.CharSequence request_id, java.lang.Long order_id, java.lang.Integer amount, java.lang.CharSequence item_id) {
    this.request_id = request_id;
    this.order_id = order_id;
    this.amount = amount;
    this.item_id = item_id;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return request_id;
    case 1: return order_id;
    case 2: return amount;
    case 3: return item_id;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: request_id = (java.lang.CharSequence)value$; break;
    case 1: order_id = (java.lang.Long)value$; break;
    case 2: amount = (java.lang.Integer)value$; break;
    case 3: item_id = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'request_id' field.
   * @return The value of the 'request_id' field.
   */
  public java.lang.CharSequence getRequestId() {
    return request_id;
  }

  /**
   * Sets the value of the 'request_id' field.
   * @param value the value to set.
   */
  public void setRequestId(java.lang.CharSequence value) {
    this.request_id = value;
  }

  /**
   * Gets the value of the 'order_id' field.
   * @return The value of the 'order_id' field.
   */
  public java.lang.Long getOrderId() {
    return order_id;
  }

  /**
   * Sets the value of the 'order_id' field.
   * @param value the value to set.
   */
  public void setOrderId(java.lang.Long value) {
    this.order_id = value;
  }

  /**
   * Gets the value of the 'amount' field.
   * @return The value of the 'amount' field.
   */
  public java.lang.Integer getAmount() {
    return amount;
  }

  /**
   * Sets the value of the 'amount' field.
   * @param value the value to set.
   */
  public void setAmount(java.lang.Integer value) {
    this.amount = value;
  }

  /**
   * Gets the value of the 'item_id' field.
   * @return The value of the 'item_id' field.
   */
  public java.lang.CharSequence getItemId() {
    return item_id;
  }

  /**
   * Sets the value of the 'item_id' field.
   * @param value the value to set.
   */
  public void setItemId(java.lang.CharSequence value) {
    this.item_id = value;
  }

  /**
   * Creates a new OrderCreatedValue RecordBuilder.
   * @return A new OrderCreatedValue RecordBuilder
   */
  public static com.example.order.OrderCreatedValue.Builder newBuilder() {
    return new com.example.order.OrderCreatedValue.Builder();
  }

  /**
   * Creates a new OrderCreatedValue RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrderCreatedValue RecordBuilder
   */
  public static com.example.order.OrderCreatedValue.Builder newBuilder(com.example.order.OrderCreatedValue.Builder other) {
    return new com.example.order.OrderCreatedValue.Builder(other);
  }

  /**
   * Creates a new OrderCreatedValue RecordBuilder by copying an existing OrderCreatedValue instance.
   * @param other The existing instance to copy.
   * @return A new OrderCreatedValue RecordBuilder
   */
  public static com.example.order.OrderCreatedValue.Builder newBuilder(com.example.order.OrderCreatedValue other) {
    return new com.example.order.OrderCreatedValue.Builder(other);
  }

  /**
   * RecordBuilder for OrderCreatedValue instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrderCreatedValue>
    implements org.apache.avro.data.RecordBuilder<OrderCreatedValue> {

    private java.lang.CharSequence request_id;
    private long order_id;
    private int amount;
    private java.lang.CharSequence item_id;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.order.OrderCreatedValue.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.request_id)) {
        this.request_id = data().deepCopy(fields()[0].schema(), other.request_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.order_id)) {
        this.order_id = data().deepCopy(fields()[1].schema(), other.order_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.amount)) {
        this.amount = data().deepCopy(fields()[2].schema(), other.amount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.item_id)) {
        this.item_id = data().deepCopy(fields()[3].schema(), other.item_id);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing OrderCreatedValue instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.order.OrderCreatedValue other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.request_id)) {
        this.request_id = data().deepCopy(fields()[0].schema(), other.request_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.order_id)) {
        this.order_id = data().deepCopy(fields()[1].schema(), other.order_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.amount)) {
        this.amount = data().deepCopy(fields()[2].schema(), other.amount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.item_id)) {
        this.item_id = data().deepCopy(fields()[3].schema(), other.item_id);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'request_id' field.
      * @return The value.
      */
    public java.lang.CharSequence getRequestId() {
      return request_id;
    }

    /**
      * Sets the value of the 'request_id' field.
      * @param value The value of 'request_id'.
      * @return This builder.
      */
    public com.example.order.OrderCreatedValue.Builder setRequestId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.request_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'request_id' field has been set.
      * @return True if the 'request_id' field has been set, false otherwise.
      */
    public boolean hasRequestId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'request_id' field.
      * @return This builder.
      */
    public com.example.order.OrderCreatedValue.Builder clearRequestId() {
      request_id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'order_id' field.
      * @return The value.
      */
    public java.lang.Long getOrderId() {
      return order_id;
    }

    /**
      * Sets the value of the 'order_id' field.
      * @param value The value of 'order_id'.
      * @return This builder.
      */
    public com.example.order.OrderCreatedValue.Builder setOrderId(long value) {
      validate(fields()[1], value);
      this.order_id = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'order_id' field has been set.
      * @return True if the 'order_id' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'order_id' field.
      * @return This builder.
      */
    public com.example.order.OrderCreatedValue.Builder clearOrderId() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'amount' field.
      * @return The value.
      */
    public java.lang.Integer getAmount() {
      return amount;
    }

    /**
      * Sets the value of the 'amount' field.
      * @param value The value of 'amount'.
      * @return This builder.
      */
    public com.example.order.OrderCreatedValue.Builder setAmount(int value) {
      validate(fields()[2], value);
      this.amount = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'amount' field has been set.
      * @return True if the 'amount' field has been set, false otherwise.
      */
    public boolean hasAmount() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'amount' field.
      * @return This builder.
      */
    public com.example.order.OrderCreatedValue.Builder clearAmount() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'item_id' field.
      * @return The value.
      */
    public java.lang.CharSequence getItemId() {
      return item_id;
    }

    /**
      * Sets the value of the 'item_id' field.
      * @param value The value of 'item_id'.
      * @return This builder.
      */
    public com.example.order.OrderCreatedValue.Builder setItemId(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.item_id = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'item_id' field has been set.
      * @return True if the 'item_id' field has been set, false otherwise.
      */
    public boolean hasItemId() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'item_id' field.
      * @return This builder.
      */
    public com.example.order.OrderCreatedValue.Builder clearItemId() {
      item_id = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrderCreatedValue build() {
      try {
        OrderCreatedValue record = new OrderCreatedValue();
        record.request_id = fieldSetFlags()[0] ? this.request_id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.order_id = fieldSetFlags()[1] ? this.order_id : (java.lang.Long) defaultValue(fields()[1]);
        record.amount = fieldSetFlags()[2] ? this.amount : (java.lang.Integer) defaultValue(fields()[2]);
        record.item_id = fieldSetFlags()[3] ? this.item_id : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrderCreatedValue>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrderCreatedValue>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrderCreatedValue>
    READER$ = (org.apache.avro.io.DatumReader<OrderCreatedValue>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
