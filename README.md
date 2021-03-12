# Magicpen Workflow

A polyglot workflow engine

## Introduction

* Can use external packages as nodes.
* It can be used as a Java lib.
* Nodes can be represented as DAG.

## Requirements

* JDK: 11

## Getting Started

1. Create a JSON file with the following content

```json
{
  "nodes": [
    {
      "id": "1",
      "action": {
        "name": "random-number"
      }
    },
    {
      "id": "2",
      "action": {
        "name": "print",
        "arguments": {
          "message": {
            "type": "NODE_RESULT",
            "value": "1:result"
          }
        }
      },
      "dependencies": [
        "1"
      ]
    }
  ]
}
```

2. Run

```bash
./gradlew :magicpen-workflow-cli:run --args workflow.json
```