<?xml version="1.0" encoding="UTF-8"?>
<sml:SMLModel xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ctx="http://www.example.org/ctx" xmlns:sml="http://www.example.org/sml">
  <elements name="LoggedIn">
    <parameter nodeReference="//@elements.0/@elements.2" name="account"/>
    <parameter nodeReference="//@elements.0/@elements.0" name="device"/>
    <elements xsi:type="sml:EntityParticipant" nodeParameter="//@elements.0/@parameter.1">
      <isOfType href="bank.ctx#//@elements.1"/>
    </elements>
    <elements xsi:type="sml:RelatorParticipant" links="//@elements.0/@elements.3 //@elements.0/@elements.4">
      <isOfType href="bank.ctx#//@elements.9"/>
    </elements>
    <elements xsi:type="sml:EntityParticipant" nodeParameter="//@elements.0/@parameter.0">
      <isOfType href="bank.ctx#//@elements.7"/>
    </elements>
    <elements xsi:type="sml:Link" entity="//@elements.0/@elements.2" relator="//@elements.0/@elements.1">
      <isOfType href="bank.ctx#//@elements.10"/>
    </elements>
    <elements xsi:type="sml:Link" entity="//@elements.0/@elements.0" relator="//@elements.0/@elements.1">
      <isOfType href="bank.ctx#//@elements.13"/>
    </elements>
  </elements>
  <elements name="OngoingSuspiciousWithdrawal">
    <parameter nodeReference="//@elements.1/@elements.2" name="account"/>
    <elements xsi:type="sml:EntityParticipant">
      <isOfType href="bank.ctx#//@elements.3"/>
    </elements>
    <elements xsi:type="sml:RelatorParticipant" reference="//@elements.1/@elements.5" links="//@elements.1/@elements.3 //@elements.1/@elements.4">
      <isOfType href="bank.ctx#//@elements.8"/>
    </elements>
    <elements xsi:type="sml:EntityParticipant" nodeParameter="//@elements.1/@parameter.0">
      <isOfType href="bank.ctx#//@elements.7"/>
    </elements>
    <elements xsi:type="sml:Link" entity="//@elements.1/@elements.0" relator="//@elements.1/@elements.1">
      <isOfType href="bank.ctx#//@elements.12"/>
    </elements>
    <elements xsi:type="sml:Link" entity="//@elements.1/@elements.2" relator="//@elements.1/@elements.1">
      <isOfType href="bank.ctx#//@elements.11"/>
    </elements>
    <elements xsi:type="sml:AttributeReference" sourceRelation="//@elements.1/@elements.7" entity="//@elements.1/@elements.1">
      <attribute href="bank.ctx#//@elements.8/@attribute.0"/>
    </elements>
    <elements xsi:type="sml:Literal" targetRelation="//@elements.1/@elements.7" value="$1000">
      <dataType href="bank.ctx#//@elements.21"/>
    </elements>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.1/@elements.5" target="//@elements.1/@elements.6">
      <relation href="bank.ctx#//@elements.15"/>
    </elements>
  </elements>
  <elements name="SuspiciousParallelLogin">
    <elements xsi:type="sml:SituationParticipant" situationType="//@elements.0" parameter="//@elements.2/@elements.3"/>
    <elements xsi:type="sml:SituationParticipant" situationType="//@elements.0" parameter="//@elements.2/@elements.2"/>
    <elements xsi:type="sml:SituationParameterReference" targetRelation="//@elements.2/@elements.4" parameter="//@elements.0/@parameter.0" situation="//@elements.2/@elements.1"/>
    <elements xsi:type="sml:SituationParameterReference" sourceRelation="//@elements.2/@elements.4" parameter="//@elements.0/@parameter.0" situation="//@elements.2/@elements.0"/>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.2/@elements.3" target="//@elements.2/@elements.2">
      <relation href="bank.ctx#//@elements.14"/>
    </elements>
  </elements>
  <elements name="SuspiciousFarawayLogin">
    <elements xsi:type="sml:SituationParticipant" sourceRelation="//@elements.3/@elements.2" situationType="//@elements.0" parameter="//@elements.3/@elements.3 //@elements.3/@elements.4" isPast="true"/>
    <elements xsi:type="sml:SituationParticipant" targetRelation="//@elements.3/@elements.2" situationType="//@elements.0" parameter="//@elements.3/@elements.5 //@elements.3/@elements.6"/>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.3/@elements.0" target="//@elements.3/@elements.1" relation="//@primitiveContextElements.5" parameter="0.2h"/>
    <elements xsi:type="sml:SituationParameterReference" sourceRelation="//@elements.3/@elements.7" parameter="//@elements.0/@parameter.0" situation="//@elements.3/@elements.0"/>
    <elements xsi:type="sml:SituationParameterReference" sourceRelation="//@elements.3/@elements.10" parameter="//@elements.0/@parameter.1" situation="//@elements.3/@elements.0"/>
    <elements xsi:type="sml:SituationParameterReference" targetRelation="//@elements.3/@elements.7" parameter="//@elements.0/@parameter.0" situation="//@elements.3/@elements.1"/>
    <elements xsi:type="sml:SituationParameterReference" sourceRelation="//@elements.3/@elements.11" parameter="//@elements.0/@parameter.1" situation="//@elements.3/@elements.1"/>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.3/@elements.3" target="//@elements.3/@elements.5">
      <relation href="bank.ctx#//@elements.14"/>
    </elements>
    <elements xsi:type="sml:EntityParticipant" targetRelation="//@elements.3/@elements.10" reference="//@elements.3/@elements.12">
      <isOfType href="bank.ctx#//@elements.1"/>
    </elements>
    <elements xsi:type="sml:EntityParticipant" targetRelation="//@elements.3/@elements.11" reference="//@elements.3/@elements.13">
      <isOfType href="bank.ctx#//@elements.1"/>
    </elements>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.3/@elements.4" target="//@elements.3/@elements.8">
      <relation href="bank.ctx#//@elements.14"/>
    </elements>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.3/@elements.6" target="//@elements.3/@elements.9">
      <relation href="bank.ctx#//@elements.14"/>
    </elements>
    <elements xsi:type="sml:AttributeReference" sourceRelation="//@elements.3/@elements.14" entity="//@elements.3/@elements.8">
      <attribute href="bank.ctx#//@elements.1/@attribute.0"/>
    </elements>
    <elements xsi:type="sml:AttributeReference" targetRelation="//@elements.3/@elements.14" entity="//@elements.3/@elements.9">
      <attribute href="bank.ctx#//@elements.1/@attribute.0"/>
    </elements>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.3/@elements.12" target="//@elements.3/@elements.13" parameter="500km" isNegated="true">
      <relation href="bank.ctx#//@elements.19"/>
    </elements>
  </elements>
  <elements name="AccountUnderObservation">
    <parameter nodeReference="//@elements.4/@elements.1" name="account"/>
    <elements xsi:type="sml:ExistsSituation" reference="//@elements.4/@elements.4" situationType="//@elements.1" parameter="//@elements.4/@elements.2" isPast="true"/>
    <elements xsi:type="sml:EntityParticipant" targetRelation="//@elements.4/@elements.3" nodeParameter="//@elements.4/@parameter.0">
      <isOfType href="bank.ctx#//@elements.7"/>
    </elements>
    <elements xsi:type="sml:SituationParameterReference" sourceRelation="//@elements.4/@elements.3" parameter="//@elements.1/@parameter.0" situation="//@elements.4/@elements.0"/>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.4/@elements.2" target="//@elements.4/@elements.1">
      <relation href="bank.ctx#//@elements.14"/>
    </elements>
    <elements xsi:type="sml:AttributeReference" sourceRelation="//@elements.4/@elements.6" entity="//@elements.4/@elements.0" attribute="//@primitiveContextElements.3/@attribute.0"/>
    <elements xsi:type="sml:Literal" targetRelation="//@elements.4/@elements.6" value="2 s" dataType="//@primitiveContextElements.2"/>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.4/@elements.4" target="//@elements.4/@elements.5" relation="//@primitiveContextElements.1" parameter=""/>
  </elements>
  <elements name="functionTest">
    <elements xsi:type="sml:EntityParticipant">
      <isOfType href="bank.ctx#//@elements.7"/>
    </elements>
    <elements xsi:type="sml:RelatorParticipant" reference="//@elements.5/@elements.5" links="//@elements.5/@elements.3 //@elements.5/@elements.4">
      <isOfType href="bank.ctx#//@elements.8"/>
    </elements>
    <elements xsi:type="sml:EntityParticipant" sourceRelation="//@elements.5/@elements.11">
      <isOfType href="bank.ctx#//@elements.3"/>
    </elements>
    <elements xsi:type="sml:Link" entity="//@elements.5/@elements.0" relator="//@elements.5/@elements.1">
      <isOfType href="bank.ctx#//@elements.11"/>
    </elements>
    <elements xsi:type="sml:Link" entity="//@elements.5/@elements.2" relator="//@elements.5/@elements.1">
      <isOfType href="bank.ctx#//@elements.12"/>
    </elements>
    <elements xsi:type="sml:AttributeReference" entity="//@elements.5/@elements.1">
      <attribute href="bank.ctx#//@elements.8/@attribute.0"/>
    </elements>
    <elements xsi:type="sml:Function">
      <parameter value="//@elements.5/@elements.5"/>
      <parameter value="//@elements.5/@elements.8"/>
      <parameter value="//@elements.5/@elements.10"/>
      <function href="bank.ctx#//@elements.22"/>
    </elements>
    <elements xsi:type="sml:Literal" value="2">
      <dataType href="bank.ctx#//@elements.2"/>
    </elements>
    <elements xsi:type="sml:Function">
      <parameter value="//@elements.5/@elements.7"/>
      <parameter value="//@elements.5/@elements.5"/>
      <function href="bank.ctx#//@elements.23"/>
    </elements>
    <elements xsi:type="sml:EntityParticipant" targetRelation="//@elements.5/@elements.11" reference="//@elements.5/@elements.10">
      <isOfType href="bank.ctx#//@elements.1"/>
    </elements>
    <elements xsi:type="sml:AttributeReference" entity="//@elements.5/@elements.9">
      <attribute href="bank.ctx#//@elements.1/@attribute.0"/>
    </elements>
    <elements xsi:type="sml:ComparativeRelation" source="//@elements.5/@elements.2" target="//@elements.5/@elements.9">
      <relation href="bank.ctx#//@elements.14"/>
    </elements>
  </elements>
  <contextModel href="bank.ctx#/"/>
  <primitiveContextElements xsi:type="ctx:DataType" name="Time"/>
  <primitiveContextElements xsi:type="ctx:PrimitiveFormalRelation" name="within the past" target="//@primitiveContextElements.0" source="//@primitiveContextElements.0"/>
  <primitiveContextElements xsi:type="ctx:DataType" name="Duration"/>
  <primitiveContextElements xsi:type="ctx:EntityClass" name="Situation" isAbstract="true">
    <attribute name="final time" datatype="//@primitiveContextElements.0"/>
    <attribute name="start time" datatype="//@primitiveContextElements.0"/>
    <attribute name="duration" datatype="//@primitiveContextElements.2"/>
  </primitiveContextElements>
  <primitiveContextElements xsi:type="ctx:PrimitiveFormalRelation" name="after" target="//@primitiveContextElements.3" source="//@primitiveContextElements.3"/>
  <primitiveContextElements xsi:type="ctx:PrimitiveFormalRelation" name="before" target="//@primitiveContextElements.3" source="//@primitiveContextElements.3"/>
</sml:SMLModel>
