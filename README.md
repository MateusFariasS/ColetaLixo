## ColetaLixo
Simulador de coleta de lixo na cidade de Teresina, baseado em programação orientada a objetos

♻️ `Simulador de Coleta de Lixo Urbano`

Este projeto é um simulador orientado a eventos para o processo de coleta de lixo urbano em uma cidade de médio porte. A simulação abrange desde a coleta nos bairros até o envio dos resíduos ao aterro sanitário, passando por estações de transferência. O objetivo principal é analisar o fluxo logístico, otimizar recursos e compreender gargalos operacionais no sistema de coleta de resíduos sólidos.

---

## `🚛 Funcionalidades`

✅ Simulação orientada a eventos com controle de tempo.

🏘️ Geração de lixo por bairros com taxas específicas.

🚚 Veículos com capacidade limitada e número máximo de viagens diárias.

🕒 Variação de tempo de trajeto com base em horários de pico e ociosidade.

♻️ Transferência de resíduos em estações intermediárias.

📈 Coleta de estatísticas: número de viagens, tempo total, carga média, entre outros.

🔄 Estrutura de dados personalizada (lista duplamente ligada circular) para modelar filas e rotas.

---

## `🛠️ Tecnologias Utilizadas`
Java 17 (ou versão compatível)

Estrutura modular com classes separadas por responsabilidade

Simulação baseada em eventos discretos

Estrutura de dados personalizada (lista duplamente ligada circular)

IDE recomendada: IntelliJ IDEA ou Eclipse

---

## `📂 Estrutura do Projeto`

```
bash
Copiar
Editar
src/
├── simulador/                     # Pacote principal do simulador
│   ├── Evento.java                # Classe base para eventos do sistema
│   ├── ListaEventos.java          # Fila de eventos futura
│   ├── Caminhao.java              # Representa os veículos coletores
│   ├── EstacaoTransferencia.java  # Lógica de estações intermediárias
│   ├── AterroSanitario.java       # Local de destino final dos resíduos
│   ├── Bairro.java                # Geração de lixo por região
│   └── Simulador.java             # Controlador geral da simulação
├── estruturas/                    # Estruturas de dados auxiliares
│   └── ListaDuplamenteLigadaCircular.java
```
---

## `📌 Objetivos do Projeto`
Simular de forma realista o processo de coleta urbana.

Identificar limitações logísticas (tempo, carga, tráfego).

Auxiliar planejamentos estratégicos com dados simulados.

Aplicar conceitos de estrutura de dados, POO e simulação de eventos.

---

## `📚 Inspiração`
Este projeto foi inspirado na dinâmica de coleta urbana da cidade de Teresina, com adaptações acadêmicas para fins de simulação computacional.

---
