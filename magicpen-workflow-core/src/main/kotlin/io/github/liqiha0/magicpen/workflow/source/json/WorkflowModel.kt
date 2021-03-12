package io.github.liqiha0.magicpen.workflow.source.json

data class WorkflowModel(
    val packages: List<PackageModel>?,
    val nodes: List<NodeModel>
)
