﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Ce code a été généré par un outil.
//     Version du runtime :4.0.30319.42000
//
//     Les modifications apportées à ce fichier peuvent provoquer un comportement incorrect et seront perdues si
//     le code est régénéré.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Lab02_b.ServiceGenNbreEntDbl {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="ServiceGenNbreEntDbl.GenererNombreEtChaineSoap")]
    public interface GenererNombreEtChaineSoap {
        
        // CODEGEN : La génération du contrat de message depuis le nom d'élément HelloWorldResult de l'espace de noms http://tempuri.org/ n'est pas marqué nillable
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/HelloWorld", ReplyAction="*")]
        Lab02_b.ServiceGenNbreEntDbl.HelloWorldResponse HelloWorld(Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/HelloWorld", ReplyAction="*")]
        System.Threading.Tasks.Task<Lab02_b.ServiceGenNbreEntDbl.HelloWorldResponse> HelloWorldAsync(Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GenererEntier", ReplyAction="*")]
        int GenererEntier(int min, int max);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GenererEntier", ReplyAction="*")]
        System.Threading.Tasks.Task<int> GenererEntierAsync(int min, int max);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GenererDouble", ReplyAction="*")]
        double GenererDouble(double a, double b);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GenererDouble", ReplyAction="*")]
        System.Threading.Tasks.Task<double> GenererDoubleAsync(double a, double b);
        
        // CODEGEN : La génération du contrat de message depuis le nom d'élément GenererChaineResult de l'espace de noms http://tempuri.org/ n'est pas marqué nillable
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GenererChaine", ReplyAction="*")]
        Lab02_b.ServiceGenNbreEntDbl.GenererChaineResponse GenererChaine(Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GenererChaine", ReplyAction="*")]
        System.Threading.Tasks.Task<Lab02_b.ServiceGenNbreEntDbl.GenererChaineResponse> GenererChaineAsync(Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class HelloWorldRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="HelloWorld", Namespace="http://tempuri.org/", Order=0)]
        public Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequestBody Body;
        
        public HelloWorldRequest() {
        }
        
        public HelloWorldRequest(Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute()]
    public partial class HelloWorldRequestBody {
        
        public HelloWorldRequestBody() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class HelloWorldResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="HelloWorldResponse", Namespace="http://tempuri.org/", Order=0)]
        public Lab02_b.ServiceGenNbreEntDbl.HelloWorldResponseBody Body;
        
        public HelloWorldResponse() {
        }
        
        public HelloWorldResponse(Lab02_b.ServiceGenNbreEntDbl.HelloWorldResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class HelloWorldResponseBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string HelloWorldResult;
        
        public HelloWorldResponseBody() {
        }
        
        public HelloWorldResponseBody(string HelloWorldResult) {
            this.HelloWorldResult = HelloWorldResult;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class GenererChaineRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="GenererChaine", Namespace="http://tempuri.org/", Order=0)]
        public Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequestBody Body;
        
        public GenererChaineRequest() {
        }
        
        public GenererChaineRequest(Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class GenererChaineRequestBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(Order=0)]
        public int longueur;
        
        public GenererChaineRequestBody() {
        }
        
        public GenererChaineRequestBody(int longueur) {
            this.longueur = longueur;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class GenererChaineResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="GenererChaineResponse", Namespace="http://tempuri.org/", Order=0)]
        public Lab02_b.ServiceGenNbreEntDbl.GenererChaineResponseBody Body;
        
        public GenererChaineResponse() {
        }
        
        public GenererChaineResponse(Lab02_b.ServiceGenNbreEntDbl.GenererChaineResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class GenererChaineResponseBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string GenererChaineResult;
        
        public GenererChaineResponseBody() {
        }
        
        public GenererChaineResponseBody(string GenererChaineResult) {
            this.GenererChaineResult = GenererChaineResult;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface GenererNombreEtChaineSoapChannel : Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class GenererNombreEtChaineSoapClient : System.ServiceModel.ClientBase<Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap>, Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap {
        
        public GenererNombreEtChaineSoapClient() {
        }
        
        public GenererNombreEtChaineSoapClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public GenererNombreEtChaineSoapClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public GenererNombreEtChaineSoapClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public GenererNombreEtChaineSoapClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        Lab02_b.ServiceGenNbreEntDbl.HelloWorldResponse Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap.HelloWorld(Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequest request) {
            return base.Channel.HelloWorld(request);
        }
        
        public string HelloWorld() {
            Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequest inValue = new Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequest();
            inValue.Body = new Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequestBody();
            Lab02_b.ServiceGenNbreEntDbl.HelloWorldResponse retVal = ((Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap)(this)).HelloWorld(inValue);
            return retVal.Body.HelloWorldResult;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<Lab02_b.ServiceGenNbreEntDbl.HelloWorldResponse> Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap.HelloWorldAsync(Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequest request) {
            return base.Channel.HelloWorldAsync(request);
        }
        
        public System.Threading.Tasks.Task<Lab02_b.ServiceGenNbreEntDbl.HelloWorldResponse> HelloWorldAsync() {
            Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequest inValue = new Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequest();
            inValue.Body = new Lab02_b.ServiceGenNbreEntDbl.HelloWorldRequestBody();
            return ((Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap)(this)).HelloWorldAsync(inValue);
        }
        
        public int GenererEntier(int min, int max) {
            return base.Channel.GenererEntier(min, max);
        }
        
        public System.Threading.Tasks.Task<int> GenererEntierAsync(int min, int max) {
            return base.Channel.GenererEntierAsync(min, max);
        }
        
        public double GenererDouble(double a, double b) {
            return base.Channel.GenererDouble(a, b);
        }
        
        public System.Threading.Tasks.Task<double> GenererDoubleAsync(double a, double b) {
            return base.Channel.GenererDoubleAsync(a, b);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        Lab02_b.ServiceGenNbreEntDbl.GenererChaineResponse Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap.GenererChaine(Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequest request) {
            return base.Channel.GenererChaine(request);
        }
        
        public string GenererChaine(int longueur) {
            Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequest inValue = new Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequest();
            inValue.Body = new Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequestBody();
            inValue.Body.longueur = longueur;
            Lab02_b.ServiceGenNbreEntDbl.GenererChaineResponse retVal = ((Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap)(this)).GenererChaine(inValue);
            return retVal.Body.GenererChaineResult;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<Lab02_b.ServiceGenNbreEntDbl.GenererChaineResponse> Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap.GenererChaineAsync(Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequest request) {
            return base.Channel.GenererChaineAsync(request);
        }
        
        public System.Threading.Tasks.Task<Lab02_b.ServiceGenNbreEntDbl.GenererChaineResponse> GenererChaineAsync(int longueur) {
            Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequest inValue = new Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequest();
            inValue.Body = new Lab02_b.ServiceGenNbreEntDbl.GenererChaineRequestBody();
            inValue.Body.longueur = longueur;
            return ((Lab02_b.ServiceGenNbreEntDbl.GenererNombreEtChaineSoap)(this)).GenererChaineAsync(inValue);
        }
    }
}
