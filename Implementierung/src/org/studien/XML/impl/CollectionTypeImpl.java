//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vFCS 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2003.02.06 at 03:42:14 PST 
//


package test.jaxb.impl;

public class CollectionTypeImpl implements test.jaxb.CollectionType, com.sun.xml.bind.RIElement, com.sun.xml.bind.unmarshaller.UnmarshallableObject, com.sun.xml.bind.serializer.XMLSerializable, com.sun.xml.bind.validator.ValidatableObject
{

    protected test.jaxb.CollectionType.BooksType _Books;
    private final static com.sun.msv.grammar.Grammar schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize("\u00ac\u00ed\u0000\u0005sr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelt\u0000 Lcom/sun/msv/grammar/Expression;xr\u0000\u001ecom.sun.msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0003xp\u0000\u00a6\u008f&pp\u0000sq\u0000~\u0000\u0000\u0000\u00a6\u008f\u001bpp\u0000sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1q\u0000~\u0000\u0003L\u0000\u0004exp2q\u0000~\u0000\u0003xq\u0000~\u0000\u0004\u0000\u00a6\u008f\u0010ppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0003xq\u0000~\u0000\u0004\u0000\u00a6\u008f\u0005sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0003L\u0000\tnameClassq\u0000~\u0000\u0001xq\u0000~\u0000\u0004\u0000\u00a6\u008f\u0002q\u0000~\u0000\u000fpsr\u00002com.sun.msv.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004\u0000\u0000\u0000\bsq\u0000~\u0000\u000e\u0001psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004\u0000\u0000\u0000\tq\u0000~\u0000\u0014psr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000\u001bxq\u0000~\u0000\u0016t\u0000\"test.jaxb.CollectionType.BooksTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\u001at\u0000\u0005bookst\u0000\u0000sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000#[\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/Expression;xp\u0000\u0000\u0000\u0002\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.grammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppq\u0000~\u0000\rppppppppppq\u0000~\u0000\nppppppppppppppppppppppppppppppppppppppppppppppppp");

    public java.lang.String ____jaxb_ri____getNamespaceURI() {
        return "";
    }

    public java.lang.String ____jaxb_ri____getLocalName() {
        return "books";
    }

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return test.jaxb.CollectionType.class;
    }

    public test.jaxb.CollectionType.BooksType getBooks() {
        return _Books;
    }

    public void setBooks(test.jaxb.CollectionType.BooksType value) {
        _Books = value;
    }

    public com.sun.xml.bind.unmarshaller.ContentHandlerEx getUnmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
        return new test.jaxb.impl.CollectionTypeImpl.Unmarshaller(context);
    }

    public java.lang.Class getPrimaryInterfaceClass() {
        return PRIMARY_INTERFACE_CLASS();
    }

    public void serializeElements(com.sun.xml.bind.serializer.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("", "books");
        context.childAsAttributes(((com.sun.xml.bind.serializer.XMLSerializable) _Books));
        context.endAttributes();
        context.childAsElements(((com.sun.xml.bind.serializer.XMLSerializable) _Books));
        context.endElement();
    }

    public void serializeAttributes(com.sun.xml.bind.serializer.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public void serializeAttributeBodies(com.sun.xml.bind.serializer.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (test.jaxb.CollectionType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public static class BooksTypeImpl implements test.jaxb.CollectionType.BooksType, com.sun.xml.bind.unmarshaller.UnmarshallableObject, com.sun.xml.bind.serializer.XMLSerializable, com.sun.xml.bind.validator.ValidatableObject
    {

        protected com.sun.xml.bind.util.ListImpl _Book = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        private final static com.sun.msv.grammar.Grammar schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize("\u00ac\u00ed\u0000\u0005sr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expt\u0000 Lcom/sun/msv/grammar/Expression;xr\u0000\u001ecom.sun.msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\u0000\u00a6\u008f)ppsr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0000\u00a6\u008f&pp\u0000sq\u0000~\u0000\u0006\u0000\u00a6\u008f\u001bpp\u0000sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1q\u0000~\u0000\u0002L\u0000\u0004exp2q\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0000\u00a6\u008f\u0010ppsq\u0000~\u0000\u0000\u0000\u00a6\u008f\u0005sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u0007xq\u0000~\u0000\u0003\u0000\u00a6\u008f\u0002q\u0000~\u0000\u0010psr\u00002com.sun.msv.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\bsq\u0000~\u0000\u000f\u0001psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tq\u0000~\u0000\u0015psr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000\u001cxq\u0000~\u0000\u0017t\u0000\u0012test.jaxb.BookTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\u001bt\u0000\u0004bookt\u0000\u0000sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000$[\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/Expression;xp\u0000\u0000\u0000\u0003\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.grammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppq\u0000~\u0000\u000eppppppppppq\u0000~\u0000\rppppppppppppppppppppppppq\u0000~\u0000\u0005pppppppppppppppppppppppp");

        private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
            return test.jaxb.CollectionType.BooksType.class;
        }

        public java.util.List getBook() {
            return _Book;
        }

        public com.sun.xml.bind.unmarshaller.ContentHandlerEx getUnmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
            return new test.jaxb.impl.CollectionTypeImpl.BooksTypeImpl.Unmarshaller(context);
        }

        public java.lang.Class getPrimaryInterfaceClass() {
            return PRIMARY_INTERFACE_CLASS();
        }

        public void serializeElements(com.sun.xml.bind.serializer.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            int idx1 = 0;
            final int len1 = _Book.size();
            while (idx1 != len1) {
                context.startElement("", "book");
                int idx_0 = idx1;
                context.childAsAttributes(((com.sun.xml.bind.serializer.XMLSerializable) _Book.get(idx_0 ++)));
                context.endAttributes();
                context.childAsElements(((com.sun.xml.bind.serializer.XMLSerializable) _Book.get(idx1 ++)));
                context.endElement();
            }
        }

        public void serializeAttributes(com.sun.xml.bind.serializer.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            int idx1 = 0;
            final int len1 = _Book.size();
        }

        public void serializeAttributeBodies(com.sun.xml.bind.serializer.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            int idx1 = 0;
            final int len1 = _Book.size();
        }

        public java.lang.Class getPrimaryInterface() {
            return (test.jaxb.CollectionType.BooksType.class);
        }

        public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
            return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
        }

        public class Unmarshaller
            extends com.sun.xml.bind.unmarshaller.ContentHandlerEx
        {


            public Unmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
                super(context, "----");
            }

            protected com.sun.xml.bind.unmarshaller.UnmarshallableObject owner() {
                return test.jaxb.impl.CollectionTypeImpl.BooksTypeImpl.this;
            }

            public void enterElement(java.lang.String ___uri, java.lang.String ___local, org.xml.sax.Attributes __atts)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                switch (state) {
                    case  3 :
                        if (("" == ___uri)&&("book" == ___local)) {
                            context.pushAttributes(__atts);
                            goto1();
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, __atts);
                        return ;
                    case  0 :
                        if (("" == ___uri)&&("book" == ___local)) {
                            context.pushAttributes(__atts);
                            goto1();
                            return ;
                        }
                        break;
                    case  1 :
                        if (("" == ___uri)&&("name" == ___local)) {
                            _Book.add(((test.jaxb.impl.BookTypeImpl) spawnChildFromEnterElement((test.jaxb.impl.BookTypeImpl.class), 2, ___uri, ___local, __atts)));
                            return ;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, __atts);
            }

            public void leaveElement(java.lang.String ___uri, java.lang.String ___local)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                switch (state) {
                    case  3 :
                        revertToParentFromLeaveElement(___uri, ___local);
                        return ;
                    case  2 :
                        if (("" == ___uri)&&("book" == ___local)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local);
            }

            public void enterAttribute(java.lang.String ___uri, java.lang.String ___local)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                switch (state) {
                    case  3 :
                        revertToParentFromEnterAttribute(___uri, ___local);
                        return ;
                    case  1 :
                        if (("" == ___uri)&&("itemId" == ___local)) {
                            _Book.add(((test.jaxb.impl.BookTypeImpl) spawnChildFromEnterAttribute((test.jaxb.impl.BookTypeImpl.class), 2, ___uri, ___local)));
                            return ;
                        }
                        break;
                }
                super.enterAttribute(___uri, ___local);
            }

            public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                switch (state) {
                    case  3 :
                        revertToParentFromLeaveAttribute(___uri, ___local);
                        return ;
                }
                super.leaveAttribute(___uri, ___local);
            }

            public void text(java.lang.String value)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                try {
                    switch (state) {
                        case  3 :
                            revertToParentFromText(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
            }

            public void leaveChild(int nextState)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                switch (nextState) {
                    case  2 :
                        state = 2;
                        return ;
                }
                super.leaveChild(nextState);
            }

            private void goto1()
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                int idx;
                state = 1;
                idx = context.getAttribute("", "itemId");
                if (idx >= 0) {
                    context.consumeAttribute(idx);
                    return ;
                }
            }

        }

    }

    public class Unmarshaller
        extends com.sun.xml.bind.unmarshaller.ContentHandlerEx
    {


        public Unmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
            super(context, "----");
        }

        protected com.sun.xml.bind.unmarshaller.UnmarshallableObject owner() {
            return test.jaxb.impl.CollectionTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, org.xml.sax.Attributes __atts)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  1 :
                    if (("" == ___uri)&&("book" == ___local)) {
                        _Books = ((test.jaxb.impl.CollectionTypeImpl.BooksTypeImpl) spawnChildFromEnterElement((test.jaxb.impl.CollectionTypeImpl.BooksTypeImpl.class), 2, ___uri, ___local, __atts));
                        return ;
                    }
                    break;
                case  0 :
                    if (("" == ___uri)&&("books" == ___local)) {
                        context.pushAttributes(__atts);
                        state = 1;
                        return ;
                    }
                    break;
                case  3 :
                    revertToParentFromEnterElement(___uri, ___local, __atts);
                    return ;
            }
            super.enterElement(___uri, ___local, __atts);
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  2 :
                    if (("" == ___uri)&&("books" == ___local)) {
                        context.popAttributes();
                        state = 3;
                        return ;
                    }
                    break;
                case  3 :
                    revertToParentFromLeaveElement(___uri, ___local);
                    return ;
            }
            super.leaveElement(___uri, ___local);
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  3 :
                    revertToParentFromEnterAttribute(___uri, ___local);
                    return ;
            }
            super.enterAttribute(___uri, ___local);
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  3 :
                    revertToParentFromLeaveAttribute(___uri, ___local);
                    return ;
            }
            super.leaveAttribute(___uri, ___local);
        }

        public void text(java.lang.String value)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            try {
                switch (state) {
                    case  3 :
                        revertToParentFromText(value);
                        return ;
                }
            } catch (java.lang.RuntimeException e) {
                handleUnexpectedTextException(value, e);
            }
        }

        public void leaveChild(int nextState)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (nextState) {
                case  2 :
                    state = 2;
                    return ;
            }
            super.leaveChild(nextState);
        }

    }

}
