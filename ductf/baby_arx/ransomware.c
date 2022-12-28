void main(undefined8 *param_1,void **param_2,wchar_t **param_3,undefined8 ****param_4)

{
  longlong lVar1;
  code *pcVar2;
  ulonglong uVar3;
  void *pvVar4;
  bool bVar5;
  undefined8 uVar6;
  void *pvVar7;
  undefined (*pauVar8) [32];
  undefined (*pauVar9) [32];
  undefined8 ****ppppuVar10;
  ulonglong uVar11;
  ulonglong uVar12;
  undefined (*pauVar13) [32];
  ulonglong uVar14;
  wchar_t *pwVar15;
  ulonglong uVar16;
  undefined8 *******pppppppuVar17;
  longlong lVar18;
  ulonglong uVar19;
  ulonglong uVar20;
  undefined auStackY392 [32];
  undefined (*local_140) [32];
  undefined4 uStack312;
  undefined4 uStack308;
  ulonglong local_130;
  ulonglong uStack296;
  undefined8 ****local_120;
  undefined4 uStack280;
  undefined4 uStack276;
  longlong local_110;
  ulonglong uStack264;
  undefined (*local_100) [32];
  ulonglong local_f0;
  ulonglong local_e8;
  undefined8 ****local_e0 [2];
  ulonglong local_d0;
  ulonglong local_c8;
  undefined (*local_c0) [32];
  undefined4 uStack184;
  undefined4 uStack180;
  ulonglong local_b0;
  ulonglong uStack168;
  undefined8 *******local_a0 [2];
  ulonglong local_90;
  ulonglong local_88;
  void *local_80;
  undefined8 local_70;
  ulonglong local_68;
  undefined4 local_60;
  undefined4 uStack92;
  undefined4 uStack88;
  undefined4 uStack84;
  undefined4 local_50;
  undefined4 uStack76;
  undefined4 uStack72;
  undefined4 uStack68;
  ulonglong local_40;
  
  local_40 = DAT_140024020 ^ (ulonglong)auStackY392;
  *param_1 = 0xffffffff80000002;
  local_f0 = 0;
  local_e8 = 7;
  local_100 = (undefined (*) [32])0x0;
  uVar6 = 0xf;
  FUN_1400055b0((undefined (*) [32])&local_100,(undefined (*) [32])L"SOFTWARE\\dotnet",0xf);
  local_130 = 0;
  uStack296 = 7;
  local_140 = (undefined (*) [32])0x0;
  uVar19 = uStack296;
  pauVar13 = local_140;
  if ((s_d38cc827-e34f-4453-9df4-1e796e9f_1400240a0[0] != 'e') ||
     (uVar6 = FUN_14000f430(L"_DOTNET_TEST_REGISTRY_PATH",(undefined (*) [32])&local_140,uVar6,
                            param_4), uVar19 = uStack296, pauVar13 = local_140, (char)uVar6 == '\0')
     ) goto LAB_14000e7dc;
  local_d0 = 0;
  local_c8 = 7;
  local_e0[0] = (undefined8 ****)0x0;
  FUN_1400055b0((undefined (*) [32])local_e0,(undefined (*) [32])L"HKEY_CURRENT_USER\\",0x12);
  uVar19 = local_d0;
  local_a0[0] = (undefined8 *******)0x0;
  local_90 = 0;
  local_88 = 7;
  uVar14 = local_d0;
  if (local_130 < local_d0) {
    uVar14 = local_130;
  }
  pauVar13 = (undefined (*) [32])&local_140;
  if (7 < uStack296) {
    pauVar13 = local_140;
  }
  FUN_1400055b0((undefined (*) [32])local_a0,pauVar13,uVar14);
  ppppuVar10 = local_e0;
  if (7 < local_c8) {
    ppppuVar10 = local_e0[0];
  }
  pppppppuVar17 = local_a0;
  if (7 < local_88) {
    pppppppuVar17 = local_a0[0];
  }
  if (local_90 == uVar19) {
    if (local_90 != 0) {
      lVar18 = (longlong)pppppppuVar17 - (longlong)ppppuVar10;
      uVar14 = local_90;
      do {
        if (*(short *)((longlong)ppppuVar10 + lVar18) != *(short *)ppppuVar10) goto LAB_14000e478;
        ppppuVar10 = (undefined8 ****)((longlong)ppppuVar10 + 2);
        uVar14 = uVar14 - 1;
      } while (uVar14 != 0);
    }
    bVar5 = true;
  }
  else {
LAB_14000e478:
    bVar5 = false;
  }
  param_4 = local_a0[0];
  if (7 < local_88) {
    if ((0xfff < local_88 * 2 + 2) &&
       (param_4 = local_a0[0][-1],
       0x1f < (ulonglong)((longlong)local_a0[0] + (-8 - (longlong)param_4)))) goto LAB_14000e77d;
    free(param_4);
  }
  uVar3 = uStack296;
  pauVar13 = local_140;
  uVar16 = 0x7ffffffffffffffe;
  uVar14 = 0xfffffffffffffffe;
  if (!bVar5) {
LAB_14000e653:
    uVar20 = local_e8;
    uVar19 = uStack296;
    uVar3 = local_130;
    pauVar13 = local_140;
    pauVar9 = (undefined (*) [32])&local_140;
    if (7 < uStack296) {
      pauVar9 = local_140;
    }
    if (local_e8 < local_130) {
      if (0x7ffffffffffffffe < local_130) {
LAB_14000ea89:
        FUN_1400014b0();
        pcVar2 = (code *)swi(3);
        (*pcVar2)();
        return;
      }
      uVar12 = local_130 | 7;
      if ((uVar12 < 0x7fffffffffffffff) && (local_e8 <= 0x7ffffffffffffffe - (local_e8 >> 1))) {
        uVar14 = (local_e8 >> 1) + local_e8;
        uVar16 = uVar12;
        if (uVar12 < uVar14) {
          uVar16 = uVar14;
        }
        if (0x7fffffffffffffff < uVar16 + 1) goto LAB_14000ea7d;
        uVar14 = (uVar16 + 1) * 2;
        if (0xfff < uVar14) goto LAB_14000e6f2;
        if (uVar14 == 0) {
          pauVar8 = (undefined (*) [32])0x0;
        }
        else {
          pauVar8 = (undefined (*) [32])operator_new(uVar14);
        }
      }
      else {
LAB_14000e6f2:
        if (uVar14 + 0x27 <= uVar14) goto LAB_14000ea7d;
        pvVar7 = operator_new(uVar14 + 0x27);
        if (pvVar7 == (void *)0x0) goto LAB_14000e77d;
        pauVar8 = (undefined (*) [32])((longlong)pvVar7 + 0x27U & 0xffffffffffffffe0);
        *(void **)(pauVar8[-1] + 0x18) = pvVar7;
      }
      local_f0 = uVar3;
      uVar3 = uVar3 * 2;
      local_e8 = uVar16;
      FUN_1400150a0(pauVar8,pauVar9,uVar3);
      *(undefined2 *)(*pauVar8 + uVar3) = 0;
      if (7 < uVar20) {
        pauVar9 = local_100;
        if ((0xfff < uVar20 * 2 + 2) &&
           (pauVar9 = *(undefined (**) [32])(local_100[-1] + 0x18),
           0x1f < (ulonglong)((longlong)local_100 + (-8 - (longlong)pauVar9)))) {
LAB_14000e77d:
                    /* WARNING: Subroutine does not return */
          _invalid_parameter_noinfo_noreturn();
        }
        free(pauVar9);
      }
    }
    else {
      pauVar8 = (undefined (*) [32])&local_100;
      if (7 < local_e8) {
        pauVar8 = local_100;
      }
      local_f0 = local_130;
      uVar14 = local_130 * 2;
      FUN_1400150a0(pauVar8,pauVar9,uVar14);
      *(undefined2 *)(*pauVar8 + uVar14) = 0;
      pauVar8 = local_100;
    }
    local_100 = pauVar8;
    if (7 < local_c8) {
      ppppuVar10 = local_e0[0];
      if ((0xfff < local_c8 * 2 + 2) &&
         (ppppuVar10 = (undefined8 ****)local_e0[0][-1],
         0x1f < (ulonglong)((longlong)local_e0[0] + (-8 - (longlong)ppppuVar10)))) {
                    /* WARNING: Subroutine does not return */
        _invalid_parameter_noinfo_noreturn();
      }
      free(ppppuVar10);
    }
LAB_14000e7dc:
    local_70 = 0;
    local_68 = 7;
    local_80 = (void *)0x0;
    pwVar15 = L"\\Setup\\InstalledVersions\\";
    FUN_1400055b0((undefined (*) [32])&local_80,(undefined (*) [32])L"\\Setup\\InstalledVersions\\",
                  0x19);
    pauVar9 = (undefined (*) [32])&local_100;
    pauVar8 = FUN_1400100b0((undefined (*) [32])&local_80,pwVar15,pauVar9,param_4);
    local_120 = *(undefined8 *****)*pauVar8;
    uStack280 = *(undefined4 *)(*pauVar8 + 8);
    uStack276 = *(undefined4 *)(*pauVar8 + 0xc);
    local_110 = *(longlong *)(*pauVar8 + 0x10);
    uStack264 = *(ulonglong *)(*pauVar8 + 0x18);
    *(undefined8 *)(*pauVar8 + 0x10) = 0;
    *(undefined8 *)(*pauVar8 + 0x18) = 7;
    *(undefined2 *)*pauVar8 = 0;
    if (uStack264 - local_110 < 3) {
      pauVar9 = FUN_140005bd0((undefined (*) [32])&local_120,3,pauVar9,
                              (undefined (*) [32])&DAT_14001d460,3);
    }
    else {
      lVar18 = local_110 + 3;
      ppppuVar10 = &local_120;
      if (7 < uStack264) {
        ppppuVar10 = local_120;
      }
      lVar1 = local_110 * 2;
      local_110 = lVar18;
      FUN_1400150a0((undefined (*) [32])((longlong)ppppuVar10 + lVar1),
                    (undefined (*) [32])&DAT_14001d460,6);
      *(undefined2 *)((longlong)ppppuVar10 + lVar18 * 2) = 0;
      pauVar9 = (undefined (*) [32])&local_120;
    }
    local_60 = *(undefined4 *)*pauVar9;
    uStack92 = *(undefined4 *)(*pauVar9 + 4);
    uStack88 = *(undefined4 *)(*pauVar9 + 8);
    uStack84 = *(undefined4 *)(*pauVar9 + 0xc);
    local_50 = *(undefined4 *)(*pauVar9 + 0x10);
    uStack76 = *(undefined4 *)(*pauVar9 + 0x14);
    uStack72 = *(undefined4 *)(*pauVar9 + 0x18);
    uStack68 = *(undefined4 *)(*pauVar9 + 0x1c);
    *(undefined8 *)(*pauVar9 + 0x10) = 0;
    *(undefined8 *)(*pauVar9 + 0x18) = 7;
    *(undefined2 *)*pauVar9 = 0;
    FUN_140005190(param_2,(void **)&local_60);
    if (7 < CONCAT44(uStack68,uStack72)) {
      pvVar4 = (void *)CONCAT44(uStack92,local_60);
      pvVar7 = pvVar4;
      if ((0xfff < CONCAT44(uStack68,uStack72) * 2 + 2) &&
         (pvVar7 = *(void **)((longlong)pvVar4 + -8),
         0x1f < (ulonglong)((longlong)pvVar4 + (-8 - (longlong)pvVar7)))) {
                    /* WARNING: Subroutine does not return */
        _invalid_parameter_noinfo_noreturn();
      }
      free(pvVar7);
    }
    if (7 < uStack264) {
      ppppuVar10 = local_120;
      if ((0xfff < uStack264 * 2 + 2) &&
         (ppppuVar10 = (undefined8 ****)local_120[-1],
         0x1f < (ulonglong)((longlong)local_120 + (-8 - (longlong)ppppuVar10)))) {
                    /* WARNING: Subroutine does not return */
        _invalid_parameter_noinfo_noreturn();
      }
      free(ppppuVar10);
    }
    local_110 = 0;
    uStack264 = 7;
    local_120 = (undefined8 ****)((ulonglong)local_120 & 0xffffffffffff0000);
    if (7 < local_68) {
      pvVar7 = local_80;
      if ((0xfff < local_68 * 2 + 2) &&
         (pvVar7 = *(void **)((longlong)local_80 + -8),
         0x1f < (ulonglong)((longlong)local_80 + (-8 - (longlong)pvVar7)))) {
                    /* WARNING: Subroutine does not return */
        _invalid_parameter_noinfo_noreturn();
      }
      free(pvVar7);
    }
    *param_3 = L"InstallLocation";
    if (7 < uVar19) {
      pauVar9 = pauVar13;
      if ((0xfff < uVar19 * 2 + 2) &&
         (pauVar9 = *(undefined (**) [32])(pauVar13[-1] + 0x18),
         0x1f < (ulonglong)((longlong)pauVar13 + (-8 - (longlong)pauVar9)))) {
                    /* WARNING: Subroutine does not return */
        _invalid_parameter_noinfo_noreturn();
      }
      free(pauVar9);
    }
    if (7 < local_e8) {
      pauVar13 = local_100;
      if ((0xfff < local_e8 * 2 + 2) &&
         (pauVar13 = *(undefined (**) [32])(local_100[-1] + 0x18),
         0x1f < (ulonglong)((longlong)local_100 + (-8 - (longlong)pauVar13)))) {
                    /* WARNING: Subroutine does not return */
        _invalid_parameter_noinfo_noreturn();
      }
      free(pauVar13);
    }
    FUN_1400137e0(local_40 ^ (ulonglong)auStackY392);
    return;
  }
  *param_1 = 0xffffffff80000001;
  pauVar9 = (undefined (*) [32])0x0;
  local_c0 = (undefined (*) [32])0x0;
  uStack168 = 7;
  if (local_130 < uVar19) goto LAB_14000ea83;
  uVar20 = 0xffffffffffffffff;
  if (local_130 - uVar19 != 0xffffffffffffffff) {
    uVar20 = local_130 - uVar19;
  }
  pauVar8 = (undefined (*) [32])&local_140;
  if (7 < uStack296) {
    pauVar8 = local_140;
  }
  if (uVar20 < 8) {
    local_b0 = uVar20;
    FUN_1400150a0((undefined (*) [32])&local_c0,(undefined (*) [32])(*pauVar8 + uVar19 * 2),
                  uVar20 * 2);
    *(undefined2 *)((longlong)&local_c0 + uVar20 * 2) = 0;
LAB_14000e5fc:
    if (7 < uVar3) {
      pauVar9 = pauVar13;
      if ((0xfff < uVar3 * 2 + 2) &&
         (pauVar9 = *(undefined (**) [32])(pauVar13[-1] + 0x18),
         0x1f < (ulonglong)((longlong)pauVar13 + (-8 - (longlong)pauVar9)))) goto LAB_14000e77d;
      free(pauVar9);
    }
    local_140 = local_c0;
    uStack312 = uStack184;
    uStack308 = uStack180;
    local_130 = local_b0;
    uStack296 = uStack168;
    goto LAB_14000e653;
  }
  if (0x7ffffffffffffffe < uVar20) goto LAB_14000ea89;
  uVar12 = uVar20 | 7;
  if (uVar12 < 0x7fffffffffffffff) {
    if (uVar12 < 10) {
      uVar12 = 10;
    }
    if (uVar12 + 1 < 0x8000000000000000) {
      uVar11 = (uVar12 + 1) * 2;
      if (0xfff < uVar11) goto LAB_14000e597;
      if (uVar11 != 0) {
        pauVar9 = (undefined (*) [32])operator_new(uVar11);
      }
      goto LAB_14000e5d5;
    }
  }
  else {
    uVar12 = 0x7ffffffffffffffe;
    uVar11 = 0xfffffffffffffffe;
LAB_14000e597:
    if (uVar11 < uVar11 + 0x27) {
      pvVar7 = operator_new(uVar11 + 0x27);
      if (pvVar7 == (void *)0x0) goto LAB_14000e77d;
      pauVar9 = (undefined (*) [32])((longlong)pvVar7 + 0x27U & 0xffffffffffffffe0);
      *(void **)(pauVar9[-1] + 0x18) = pvVar7;
LAB_14000e5d5:
      local_b0 = uVar20;
      uStack168 = uVar12;
      FUN_1400150a0(pauVar9,(undefined (*) [32])(*pauVar8 + uVar19 * 2),uVar20 * 2);
      *(undefined2 *)(*pauVar9 + uVar20 * 2) = 0;
      local_c0 = pauVar9;
      goto LAB_14000e5fc;
    }
  }
LAB_14000ea7d:
  FUN_140001410();
LAB_14000ea83:
  FUN_140005bb0();
  pcVar2 = (code *)swi(3);
  (*pcVar2)();
  return;
}