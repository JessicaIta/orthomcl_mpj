# orthomcl_mpj

Paralelização com MPJ dos três primeiros passos de execução do OrthoMCL para geração dos grupos ortólogos.

Passos:
- Adjust Fasta
- Filter Fasta
- Blastp All vs All

OBS: 
- Necessário ter o MPJ Express, OrthoMCL e o BLAST instalados.
- Importante que os arquivos fasta estejam na pasta fasta (alvo e modelos).
- Lista dos organismos modelos para serem processados no arquivo_fasta.txt.
- O número de processos deve ser igual ao número de arquivos modelo.


Para compilar os arquivos use o comando: 

```
 $ javac -cp $MPJ_HOME/lib/mpj.jar *java
```

Para executar: 

```
 $ mpjrun.sh -np 2 {nome do organismo alvo}
```

